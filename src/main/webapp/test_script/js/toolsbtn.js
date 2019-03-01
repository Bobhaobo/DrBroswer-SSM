//窗口尺寸改变响应（修改canvas大小）
      function resizeCanvas() {
        canvas.setWidth($(window).get(0).innerWidth);
        canvas.setHeight($(window).get(0).innerHeight);
      };
		//服务器上传dcm图像的路径
		var fileSrc = null;
		//基本配置
		var config = {
			imgSrc : '',    // 图片路径
			maxScale : 3.0,        // 最大放大倍数
			minScale : 0.5,        // 最小放大倍数
			step : 0.5            // 每次放大、缩小 倍数的变化值
		};

	 //执行打开窗口选择图片之后的过程
	function doUpload() {
		//var formData = new FormData($("#imageForm")[0]);
		$.ajaxFileUpload({
            type:'POST',//提交方式
            //data:formData,
            url:'/get/uploadFile',
			dataType:"json",
			fileElementId:"inputFile",
			// async:false,
			// cache:false,
			// contentType:false,
			// processData:false,
			success:function (d) {
				canvas.clear();
				if(d.code ==0){
					config.imgSrc=d.data.url;
                    fileSrc = d.data.fileUrl;
                    var image = fabric.Image.fromURL(config.imgSrc,function(oImg){
                        oImg.left = canvas.width/2;
                        oImg.top = canvas.height/2;
                        oImg.originX = 'center';
                        oImg.originY = 'center';
                        oImg.hasBorders = false;
                        oImg.hasControls = false;
                        oImg.set('selectable',false);//使对象无法选择
                        canvas.add(oImg);
                        //canvas.insertAt(oImg,0);
                        //canvas.setObject(oImg);
                        myImg = canvas.getObjects();
                    });

				}
				config.imgSrc="";
			},
			error:function () {
				alert("影像加载失败");
			}
		});
	}

	var canvas = new fabric.Canvas('canvasBackground');
	canvas.defaultCursor = 'crosshair'; //默认光标改成十字
	canvas.hoverCursor = 'pointer'; //悬浮光标改成手型
	canvas.selection = false; //禁用组选择
	  //正在绘制的对象
	  var drawingObject = null;
	  //获取图像对象
	  var myImg = null;
	  //var freeDrawing = true;
	  //计矩形框的个数
	  var countRect = 0;

	  var divPos = {};
	  var offset = $('#canvasBackground').offset();
	  $(document).mousemove(function(e){
		  divPos = {
			  left: e.pageX - offset.left,
			  top: e.pageY - offset.top
              // left:e.pageX - offset.left|| (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft) - offset.left),
              // top: e.pageY - offset.top|| (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop) - offset.top)
		  };
	  });


	  //获取打开文件按钮
	  var openFile = document.getElementById("openFile");
	  //保存文件的按钮
 	  var saveAnnotaitons = document.getElementById("saveAnnotations");
 	  //获取放大按钮
	  var zoomInImage = document.getElementById("zoomInImage");
	  //获取缩小按钮
	  var zoomOutImage = document.getElementById("zoomOutImage");
	  //获取左旋转按钮
	  var rotateCounterClockwise = document.getElementById("rotateCounterClockwise");
	  //获取右旋转按钮
	  var rotateClockwise = document.getElementById("rotateClockwise");
	  //获取恢复原图大小按钮
	  var actualSize = document.getElementById("actualSize");
	  //获取矩形框按钮
	  var rectbtn = document.getElementById("rectbtn");
	  //获取删除标记框的按钮
	  var deleteAnnotation = document.getElementById("deleteAnnotation");


	   var imgStatus = {
        'scale' : 1.0,
        'rotate' : 0
	  };
	  lastStatus = {
				"imgX" : 0,
				"imgY" : 0,
				'mouseX' : 0,
				'mouseY' : 0,
				'translateX' : 0,
				'translateY' : 0,
				'scale' : 1.0,
				'rotate' : 0
	};


		//添加窗口尺寸改变响应监听
		$(window).resize(resizeCanvas);
		//页面加载后先设置一下canvas大小
		resizeCanvas();

		/* var image = fabric.Image.fromURL(config.imgSrc,function(oImg){
			 oImg.left = canvas.width/2,
			 oImg.top = canvas.height/2,
			 oImg.originX = 'center';
			 oImg.originY = 'center';
			 oImg.hasBorders = false;
			 oImg.hasControls = false;
			 oImg.set('selectable',false);//使对象无法选择
			 canvas.add(oImg);
			 //canvas.insertAt(oImg,0);
			 //canvas.setObject(oImg);
			 myImg = canvas.getObjects();
	  	});*/

	  	//对打开文件按钮的操作
		openFile.onclick = function(){
			document.getElementById("inputFile").click();
		}

		//对保存标注按钮的操作
		saveAnnotaitons.onclick = function () {
			var xy = new Array();
            var myImg1 = canvas.getObjects();
            var picWidth = myImg1[0].width;
            var picHeight = myImg1[0].height;
            for(var i = 1;i<myImg1.length;i++){
				var x1 = myImg1[i].left-myImg1[0].left+myImg1[0].width/2;
                var y1 = myImg1[i].top-myImg1[0].top+myImg1[0].height/2;
                var x2 = x1+myImg1[i].width;
                var y2 = y1+myImg1[i].height;
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
	  //恢复原图尺寸大小按钮的操作
		  actualSize.onclick = function(){
			 imgStatus.scale = 1.0;
			 imgStatus.rotate = 0;
			 myImg[0].angle = imgStatus.rotate;
			//计算缩放中心
			 var zoomPoint = new fabric.Point(canvas.width/2,canvas.height/2);
			 //开始放大
			 canvas.zoomToPoint(zoomPoint,imgStatus.scale);
		  }
	  //放大按钮的操作
		  zoomInImage.onclick = function(){
			 imgStatus.scale = (imgStatus.scale >= config.maxScale) ? config.maxScale : imgStatus.scale + config.step;
			 //计算缩放中心
			 var zoomPoint = new fabric.Point(canvas.width/2,canvas.height/2);
			 //开始放大
			 canvas.zoomToPoint(zoomPoint,imgStatus.scale);
			 cnavas.renderAll();
		  }
		//缩小按钮的操作
		  zoomOutImage.onclick = function(){
			   imgStatus.scale = (imgStatus.scale <= config.minScale) ? config.minScale : imgStatus.scale - config.step;
			   //计算缩放中心
			 var zoomPoint = new fabric.Point(canvas.width/2,canvas.height/2);
			 //开始缩小
			 canvas.zoomToPoint(zoomPoint,imgStatus.scale);
		  }
		  //左旋转按钮的操作
		  rotateCounterClockwise.onclick = function(){
			var rotate = parseInt(imgStatus.rotate / 45) * 45 - 45;
			imgStatus.rotate = rotate;
			myImg[0].angle = imgStatus.rotate;
			canvas.renderAll();
		  }
		  //右旋转按钮的操作
		  rotateClockwise.onclick = function(){
			var rotate = parseInt(imgStatus.rotate / 45) * 45 + 45;
			imgStatus.rotate = rotate;
			myImg[0].angle = imgStatus.rotate;
			canvas.renderAll();
		  }
		  //对画矩形标注框按钮的操作
		   /*rectbtn.onclick = function(){
			   var flag= true;
				var isMouseDown = false;
				//用来存储矩形框
				var refRect;
				var refRect1;
				var startX,startY;
				canvas.on('mouse:down',function(event){
					isMouseDown = true;
					if(flag){
						startX = divPos.left;
						startY = divPos.top;
						var rect = new fabric.Rect({
							width:0,
							height:0,
							left:startX,
							top:startY,
							stroke: 'red',
							strokeWidth:2,
							fill:''
						});
						countRect++;
						canvas.add(rect);
						refRect = rect;
					}
				});
				canvas.on('mouse:move',function(event){
					if(!isMouseDown){
						return;
					}
					if(flag){
						var posX =divPos.left -startX;
						var posY = divPos.top -startY;
						refRect.set({
							width: posX,
							height: posY,
							borderColor: 'red',
							cornerColor:'#00FFFF',
							//四边的框是否为实心
							//transparentCorners:false,
							cornerSize: 10,
							lockRotation:true,
							selectable:true
						});
						canvas.renderAll();
					}



				});
				canvas.on('mouse:up',function(){
					if(!isMouseDown){
						return;
					}
					if(flag){
						canvas.add(refRect);
					}
					isMouseDown = false;
					flag = false;
					console.log()

				});

		}*/
		//对画矩形标注框按钮的操作
		rectbtn.onclick = function(){
			    var flag= true;
				var isMouseDown = false;
				//用来存储矩形框
				var startX,startY,scrollX,scrollY;
				canvas.on('mouse:down',function(event){
					isMouseDown = true;
                    //此函数为能在放大缩小情况下画框同样适用
					//为滚动的距离
                    scrollX = 234.99998474121094 - $("#canvasBackground").offset().left;
					scrollY = 137.96875 - $("#canvasBackground").offset().top;
                    startX = divPos.left+scrollX+(canvas.width/2-(divPos.left+scrollX))*(1-1.0/imgStatus.scale);
                    startY = divPos.top-15+scrollY+(canvas.height/2-(divPos.top-15+scrollY))*(1-1.0/imgStatus.scale);
				});
				canvas.on('mouse:move',function(event){
					if(!isMouseDown){
						return;
					}

					if(flag){
						draw(startX,startY);
					}
				});
				canvas.on('mouse:up',function(){
					if(!isMouseDown){
						return;
					}
					if(flag){
						//canvas.add(refRect);
					}
					drawingObject = null;;
					isMouseDown = false;
					flag = false;

				});

		}
		//画图像的函数
		function draw( x, y){
			if (drawingObject) {
				  canvas.remove(drawingObject);
				}
				var canvasObject = null;
				//鼠标的位置
				var scrollX = 234.99998474121094 - $("#canvasBackground").offset().left;
            	var scrollY = 137.96875 - $("#canvasBackground").offset().top;
				var moveX = divPos.left+scrollX+(canvas.width/2-(divPos.left+scrollX))*(1-1.0/imgStatus.scale);
				var moveY = divPos.top-15+scrollY+(canvas.height/2-(divPos.top-15+scrollY))*(1-1.0/imgStatus.scale);
				var posX = moveX -x;
				var posY = moveY -y;
				canvasObject = new fabric.Rect({
					width:posX,
					height:posY,
					left:x,
					top:y,
					stroke: 'red',
					strokeWidth:2,
					fill:'',
					borderColor: 'red',
					cornerColor:'#00FFFF',
					//四边的框是否为实心
					//transparentCorners:false,
					cornerSize: 10,
					lockRotation:true,
					selectable:true
				});
				if (canvasObject) {
				  // canvasObject.index = getCanvasObjectIndex();
				  canvas.add(canvasObject); //.setActiveObject(canvasObject)
				  drawingObject = canvasObject;
				}
		}

		//对删除标记框按钮的操作

			canvas.on("selection:created", function (e) {
			deleteAnnotation.onclick = function(){
				if (e.target._objects) {
				  //多选删除
				  var etCount = e.target._objects.length;
				  for (var etindex = 0; etindex < etCount; etindex++) {
					canvas.remove(e.target._objects[etindex]);
				  }
				} else {
					  //单选删除
                    canvas.remove(e.target);
				}
				//canvas.discardActiveObject(); //清楚选中框
			}

		  });
		
		  		  