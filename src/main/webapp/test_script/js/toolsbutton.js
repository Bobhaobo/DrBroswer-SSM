
cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

// this function gets called once the user drops the file onto the div
function handleFileSelect(evt) {
    evt.stopPropagation();
    evt.preventDefault();

    // Get the FileList object that contains the list of files that were dropped
    const files = evt.dataTransfer.files;

    // this UI is only built for a single file so just dump the first one
    file = files[0];
    const imageId = cornerstoneWADOImageLoader.wadouri.fileManager.add(file);
    loadAndViewImage(imageId);
}

function handleDragOver(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
}

// Setup the dnd listeners.
const dropZone = document.getElementById('dicomImage');
dropZone.addEventListener('dragover', handleDragOver, false);
dropZone.addEventListener('drop', handleFileSelect, false);


cornerstoneWADOImageLoader.configure({
    beforeSend: function(xhr) {
        // Add custom headers here (e.g. auth tokens)
        //xhr.setRequestHeader('x-auth-token', 'my auth token');
    },
    useWebWorkers: true,
});

let loaded = false;

//放大倍数设置
var config = {
    // invert: true,
    minScale: 0.25,
    maxScale: 10.0,
    preventZoomOutsideImage: true
};

cornerstoneTools.zoom.setConfiguration(config);


function activate(id) {
    document.querySelectorAll('button').forEach(function(elem) {
        elem.classList.remove('active');
    });

    document.getElementById(id).classList.add('active');
}
//使其他按钮失效的函数
function disableAllTools() {
    cornerstoneTools.rectangleRoi.deactivate(element,1);
    cornerstoneTools.probe.deactivate(element, 1);
    cornerstoneTools.pan.activate(element, 4); // 2是滚轮
    cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
    cornerstoneTools.eraser.deactivate(element,1);
    cornerstoneTools.length.deactivate(element,1);
    cornerstoneTools.angle.deactivate(element,1);
}


function loadAndViewImage(imageId) {
    const element = document.getElementById('dicomImage');
    //const start = new Date().getTime();
    cornerstone.loadImage(imageId).then(function(image) {
        //console.log(image);
        const viewport = cornerstone.getDefaultViewportForImage(element, image);
       /* document.getElementById('toggleModalityLUT').checked = (viewport.modalityLUT !== undefined);
        document.getElementById('toggleVOILUT').checked = (viewport.voiLUT !== undefined);*/
        cornerstone.displayImage(element, image, viewport);
        if(loaded === false) {
            cornerstoneTools.mouseInput.enable(element);
            cornerstoneTools.mouseWheelInput.enable(element);
            //1是左键，2是滚轮，3是左键和滚轮，4是右键，5是左键和右键
            //cornerstoneTools.wwwc.activate(element, 1); // ww/wc is the default tool for left mouse button
            cornerstoneTools.pan.activate(element, 4); // 2是滚轮
            cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
           // cornerstoneTools.zoomWheel.activate(element); // 通过滚轮放大缩小
            //这个是图像渲染的信息
            //cornerstoneTools.imageStats.enable(element);
            loaded = true;
        }

        function getTransferSyntax() {
            const value = image.data.string('x00020010');
            return value + ' [' + uids[value] + ']';
        }

        function getSopClass() {
            const value = image.data.string('x00080016');
            return value + ' [' + uids[value] + ']';
        }

        function getPixelRepresentation() {
            const value = image.data.uint16('x00280103');
            if(value === undefined) {
                return;
            }
            return value + (value === 0 ? ' (unsigned)' : ' (signed)');
        }

        function getPlanarConfiguration() {
            const value = image.data.uint16('x00280006');
            if(value === undefined) {
                return;
            }
            return value + (value === 0 ? ' (pixel)' : ' (plane)');
        }
        document.getElementById("topleft").innerText = "Study Date:" + image.data.string('x00080020') + '\n' +
            "StudyID:" + image.data.string('x00200010') + '\n' +
            "Series Number:" + image.data.string('x00200011');
        document.getElementById("topright").innerText = "Name:" + image.data.string('x00100010') + '\n' +
            "PID:" + image.data.string('x00100020') + '\n' +
            "Sex:" + image.data.string('x00100040');
        function onImageRendered(e) {
            var viewport1 = cornerstone.getViewport(e.target);
            document.getElementById('bottomleft').innerText = "WW/WC:" + Math.round(viewport1.voi.windowWidth) + '/' + Math.round(viewport1.voi.windowCenter);
            document.getElementById('bottomright').innerText = "Zoom:" + viewport1.scale.toFixed(2);
        }
        element.addEventListener('cornerstoneimagerendered', onImageRendered);
      /*  document.getElementById('transferSyntax').textContent = getTransferSyntax();
        document.getElementById('sopClass').textContent = getSopClass();
        document.getElementById('samplesPerPixel').textContent = image.data.uint16('x00280002');
        document.getElementById('photometricInterpretation').textContent = image.data.string('x00280004');
        document.getElementById('numberOfFrames').textContent = image.data.string('x00280008');
        document.getElementById('planarConfiguration').textContent = getPlanarConfiguration();
        document.getElementById('rows').textContent = image.data.uint16('x00280010');
        document.getElementById('columns').textContent = image.data.uint16('x00280011');
        document.getElementById('pixelSpacing').textContent = image.data.string('x00280030');
        document.getElementById('bitsAllocated').textContent = image.data.uint16('x00280100');
        document.getElementById('bitsStored').textContent = image.data.uint16('x00280101');
        document.getElementById('highBit').textContent = image.data.uint16('x00280102');
        document.getElementById('pixelRepresentation').textContent = getPixelRepresentation();
        document.getElementById('windowCenter').textContent = image.data.string('x00281050');
        document.getElementById('windowWidth').textContent = image.data.string('x00281051');
        document.getElementById('rescaleIntercept').textContent = image.data.string('x00281052');
        document.getElementById('rescaleSlope').textContent = image.data.string('x00281053');
        document.getElementById('basicOffsetTable').textContent = image.data.elements.x7fe00010 && image.data.elements.x7fe00010.basicOffsetTable ? image.data.elements.x7fe00010.basicOffsetTable.length : '';
        document.getElementById('fragments').textContent = image.data.elements.x7fe00010 && image.data.elements.x7fe00010.fragments ? image.data.elements.x7fe00010.fragments.length : '';
        document.getElementById('minStoredPixelValue').textContent = image.minPixelValue;
        document.getElementById('maxStoredPixelValue').textContent = image.maxPixelValue;
        const end = new Date().getTime();
        const time = end - start;
        document.getElementById('totalTime').textContent = time + "ms";
        document.getElementById('loadTime').textContent = image.loadTimeInMS + "ms";
        document.getElementById('decodeTime').textContent = image.decodeTimeInMS + "ms";*/

    }, function(err) {
        alert(err);
    });
}

//dcm文件路径
var fileSrc = null;
//执行打开窗口选择图片之后的过程
function doUpload() {
    //var formData = new FormData($("#imageForm")[0]);
    $.ajaxFileUpload({
        type:'POST',//提交方式
        //data:formData,
        url:'/get/uploadFile',
        dataType:"json",
        fileElementId:"inputFile",
        async:true,
        // async:false,
        cache:false,
        // contentType:false,
        // processData:false,
        success:function (d) {
            if(d.code ==0){
                fileSrc = d.data.fileUrl;
            }
            document.getElementById('inputFile').addEventListener('change', function(e) {
                // Add the file to the cornerstoneFileImageLoader and get unique
                // number for that file
                var file = e.target.files[0];
                const imageId = cornerstoneWADOImageLoader.wadouri.fileManager.add(file);
                loadAndViewImage(imageId);
                doUpload();
            });
        },
        error:function () {
            alert("影像上传失败");
        }
    });
}


//开始添加各个按钮功能
//获取打开文件按钮
var openFile = document.getElementById("openFile");
//保存文件的按钮
var saveAnnotaitons = document.getElementById("saveAnnotations");
//获取恢复原图大小按钮
var actualSize = document.getElementById("actualSize");
//获取放大按钮
var zoomInImage = document.getElementById("zoomInImage");
//获取缩小按钮
var zoomOutImage = document.getElementById("zoomOutImage");
//获取左旋转按钮
var rotateCounterClockwise = document.getElementById("rotateCounterClockwise");
//获取右旋转按钮
var rotateClockwise = document.getElementById("rotateClockwise");
//获取矩形框按钮
var rectbtn = document.getElementById("rectbtn");
//获取删除标记框的按钮
var deleteAnnotation = document.getElementById("deleteAnnotation");
//获取长度测量的按钮
var length = document.getElementById("length");
//获取角度测量的按钮
var angle = document.getElementById("angle");

//对调窗宽窗位的按钮的操作
document.getElementById("windowChange").onclick = function () {
    disableAllTools();
    let viewport = cornerstone.getViewport(element);
    viewport.voi.windowWidth = document.getElementById("windowWidth").value;
    viewport.voi.windowCenter = document.getElementById("windowCenter").value;
    cornerstone.setViewport(element, viewport);
};

//对打开文件按钮的操作
openFile.onclick = function(){
    document.getElementById("inputFile").click();
}

//对保存标注按钮的操作
saveAnnotaitons.onclick = function(e){
    disableAllTools();
    var toolStateManager = cornerstoneTools.getElementToolStateManager(element);
    var toolData = toolStateManager.get(element,"rectangleRoi");
    var imageSize = cornerstone.getImage(element);
    if(!toolData){
        alert("没有标注信息");
        return;
    }else{
        //console.log(toolData.data[1].handles.start.x);
        var xy = new Array();
        var picWidth = imageSize.data.uint16('x00280011');
        var picHeight = imageSize.data.uint16('x00280010');
        for(var i = 0;i<toolData.data.length;i++){
            var x1 =  Math.min(toolData.data[i].handles.start.x,toolData.data[i].handles.end.x);
            var y1 =  Math.min(toolData.data[i].handles.start.y,toolData.data[i].handles.end.y);
            var x2 =  Math.max(toolData.data[i].handles.start.x,toolData.data[i].handles.end.x);
            var y2 =  Math.max(toolData.data[i].handles.start.y,toolData.data[i].handles.end.y);
            //xy[i-1]=new Array(x1,y1,x2,y2);
            xy.push(x1,y1,x2,y2);

        }
        $.ajax({
            async:false,
            cache:false,
            traditional:true,//
            type: 'post',
            dataType: 'json',
            url: '/get/receiveList',
            /*data: {xy:JSON.stringify(xy)},*/
            data : {
                fileSrc:fileSrc,
                xy:xy,
                width:picWidth,
                height:picHeight
            },
            success: function (data) {
                if(data == "1") {
                    alert("保存成功");
                }
                if (data == "0"){
                    alert("保存失败")
                }
            },
            error: function(){
                alert("保存失败");
            }
        });

    }

}


//对实际尺寸按钮的操作
actualSize.onclick = function(){
    disableAllTools();
    cornerstone.reset(element);
}

//放大按钮的操作
zoomInImage.onclick = function(){
    disableAllTools();
    let viewport = cornerstone.getViewport(element);
    viewport.scale += 0.3;
    cornerstone.setViewport(element, viewport);
}

//缩小按钮的操作
zoomOutImage.onclick = function(){
    disableAllTools();
    let viewport = cornerstone.getViewport(element);
    viewport.scale -= 0.3;
    cornerstone.setViewport(element, viewport);
}

//左旋转按钮的操作
rotateCounterClockwise.onclick = function(){
    disableAllTools();
    const viewport = cornerstone.getViewport(element);
    viewport.rotation -= 90;
    cornerstone.setViewport(element, viewport);
}

//右旋转按钮的操作
rotateClockwise.onclick = function(){
    disableAllTools();
    const viewport = cornerstone.getViewport(element);
    viewport.rotation += 90;
    cornerstone.setViewport(element, viewport);
}

//对删除按钮的操作
deleteAnnotation.onclick = function(){
    activate("deleteAnnotation");
    disableAllTools();
    cornerstoneTools.rectangleRoi.enable(element,1);
    cornerstoneTools.probe.enable(element, 1);
    cornerstoneTools.length.enable(element,1);
    cornerstoneTools.angle.enable(element,1);
    cornerstoneTools.eraser.enable(element, 1);
    cornerstoneTools.eraser.activate(element, 1);
}

//对画标注框按钮的操作
rectbtn.onclick = function(e){
    activate("rectbtn");
    disableAllTools();
    cornerstoneTools.rectangleRoi.activate(element, 1);

}

//对长度测量按钮的操作
length.onclick = function () {
    activate("length");
    disableAllTools();
    cornerstoneTools.length.activate(element,1);
}

//对角度测量按钮的操作
angle.onclick = function () {
    activate("angle");
    disableAllTools();
    cornerstoneTools.angle.activate(element,1);
}

/*cornerstone.events.addEventListener('cornerstoneimageloadprogress', function(event) {
    const eventData = event.detail;
    const loadProgress = document.getElementById('loadProgress');
    loadProgress.textContent = `Image Load Progress: ${eventData.percentComplete}%`;
});*/

const element = document.getElementById('dicomImage');
cornerstone.enable(element);

document.getElementById('inputFile').addEventListener('change', function(e) {
    // Add the file to the cornerstoneFileImageLoader and get unique
    // number for that file
    var file = e.target.files[0];

    const imageId = cornerstoneWADOImageLoader.wadouri.fileManager.add(file);
    loadAndViewImage(imageId);
    doUpload();
});

//适应窗口大小
function imageSize() {
    var imgArea = document.getElementById("imgArea");
    imgArea.style.height = (document.documentElement.clientHeight)*0.65+"px";
    imgArea.style.width = (document.documentElement.clientWidth)*0.75+"px";
    element.style.height = (document.documentElement.clientHeight)*0.65+"px";
    element.style.width = (document.documentElement.clientWidth)*0.75+"px";
    element.getElementsByTagName("canvas")[0].height = (document.documentElement.clientHeight)*0.65;
    element.getElementsByTagName("canvas")[0].width = (document.documentElement.clientWidth)*0.75;
    element.getElementsByTagName("canvas")[0].style.height = (document.documentElement.clientHeight)*0.65+"px";
    element.getElementsByTagName("canvas")[0].style.width = (document.documentElement.clientWidth)*0.75+"px";
}
imageSize();
$(window).resize(function(){
    imageSize();
    cornerstone.resize(element);
});

/*
document.getElementById('toggleModalityLUT').addEventListener('click', function() {
    const applyModalityLUT = document.getElementById('toggleModalityLUT').checked;
    //console.log('applyModalityLUT=', applyModalityLUT);
    const image = cornerstone.getImage(element);
    const viewport = cornerstone.getViewport(element);
    if(applyModalityLUT) {
        viewport.modalityLUT = image.modalityLUT;
    } else {
        viewport.modalityLUT = undefined;
    }
    cornerstone.setViewport(element, viewport);
});

document.getElementById('toggleVOILUT').addEventListener('click', function() {
    const applyVOILUT = document.getElementById('toggleVOILUT').checked;
   // console.log('applyVOILUT=', applyVOILUT);
    const image = cornerstone.getImage(element);
    const viewport = cornerstone.getViewport(element);
    if(applyVOILUT) {
        viewport.voiLUT = image.voiLUT;
    } else {
        viewport.voiLUT = undefined;
    }
    cornerstone.setViewport(element, viewport);
});
*/
