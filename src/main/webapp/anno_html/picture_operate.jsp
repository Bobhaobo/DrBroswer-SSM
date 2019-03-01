<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/tag.jsp"%>
<%@ include file="common/head.jsp"%>
<!DOCTYPE html
PUBLIC  "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh" lang="zh-cn">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" type="image/x-icon" href="../anno_script/Images/favicon.ico" />
    <title>Annotations Demo</title>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=IE8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />

    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <link rel="Stylesheet" href="../anno_script/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../anno_script/css/Site.css" type="text/css" />
    <link rel="stylesheet" href="../anno_script/css/Toolbar.css" type="text/css" />
    <link rel="stylesheet" href="../anno_script/css/Icons.css" type="text/css" />


    <script src="../anno_script/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../anno_script/js/bootstrap.min.js"></script>
    <!-- 选择完文件之后调用上传函数 -->
    <script type="text/javascript">

    </script>
</head>
<body style= "width:100%;height:100%;overflow:auto;">
<!-- Create a header toolbar -->
<div id="tollHead" class="toolbar headerToolbarDiv">
    <form id="imageForm"  method="post"  content="multipart/form-data" style="display:none">
        <input type="file" id="inputFile" name="inputFile"  />
    </form>
    <button  id="openFile" class="btn btn-default" title="打开文件">
        <span class="icon openIcon"></span>
    </button>
    <button id="saveAnnotations" class="btn btn-default" title="保存标注">
        <span class="icon saveXMLIcon"></span>
    </button>
    <!--<button id="panZoom" class="btn btn-defalut" title="移动">
        <span class="icon panZoomIcon"></span>
    </button>-->
    <button id="actualSize" class="btn btn-default" title="实际尺寸">
        <span class="icon actualSizeIcon"></span>
    </button>
    <!--button id ="magnifyGlass"class="btn btn-defalut interactiveModesBtns" title="放大镜" value="1">
       <span class="icon magnifyGlassIcon"></span>
    </button-->
    <button id="zoomInImage" class="btn btn-default" title="放大">
        <span class="icon zoomInIcon"></span>
    </button>
    <button id="zoomOutImage" class="btn btn-default" title="缩小">
        <span class="icon zoomOutIcon"></span>
    </button>
    <button id="rotateCounterClockwise" class="btn btn-default" title="左旋转">
        <span class="icon rotateCounterClockwiseIcon"></span>
    </button>
    <button id="rotateClockwise" class="btn btn-default" title="右旋转">
        <span class="icon rotateClockwiseIcon"></span>
    </button>
    <!--
    <button id="loadAnnotations" class="btn btn-default" title="Load Annotations">
       <span class="icon openAnnotationIcon"></span>
    </button>
    <button id="saveAnnotations" class="btn btn-default" title="Save Annotations">
       <span class="icon saveXMLIcon"></span>
    </button>

    <button id="undo" class="btn btn-default" title="撤回">
       <span class="icon undoIcon"></span>
    </button>-->
    <!--
    <button id="redo" class="btn btn-default" title="Redo">
       <span class="icon redoIcon"></span>
    </button>-->
    <button id="deleteAnnotation" class="btn btn-default" title="删除标记">
        <span class="icon closeIcon"></span>
    </button>
    <!--<button class="annotationObjectBtn btn btn-default" title="直线">
       <span class="icon lineIcon" ></span>
    </button-->
    <button id="rectbtn" class="annotationObjectBtn btn btn-default" title="矩形" data-type="rectangle">
        <span class="icon rectangleIcon" data-type="rectangle"></span>
    </button>
    <!---<button class="annotationObjectBtn btn btn-default" title="多边形">
       <span class="icon polygonIcon"></span>
    </button>-->
</div>
<div id="imageViewerDiv">
    <canvas id="canvasBackground" width="1920" height="1080" style="display: block;margin:0px auto;">
        您的浏览器不支持canvas
    </canvas>
    <%--<img id="photo" src="" alt="图片"/>--%>
</div>
</body>
<script type="text/javascript" src="../anno_script/js/fabric.min.js"></script>
<script type="text/javascript" src="../anno_script/js/jcanvas.min.js"></script>
<script type="text/javascript" src="../anno_script/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="../anno_script/js/toolsbtn.js"></script>
</html>