<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banner管理</title>
    <script src="/resource/js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="/viewjs/banner/banneradd.js"></script>

    <script charset="utf-8" src="/resource/js/kindeditor-4.1.10/kindeditor-min.js"></script>
    <script charset="utf-8" src="/resource/js/lang/zh_CN.js"></script>
    <script src="/resource/js/jquery.form.js"></script>

    <style>
        .city-area select {
            margin-right: 1%;
            width: 48%;
        }

        .phone input {
            margin-right: 1%;
            width: 32%;
        }

        .import-pic {
            position: relative;
            left: 25%;
            margin-left: -25px;
            display: inline-block;
            overflow: hidden;
            text-align: center;
        }

        .radio-box label {
            position: relative;
            top: 4px;
            margin-right: 30px;
            cursor: pointer;
        }

        legend {
            border-bottom: none;
        }

        i[class="red"] {
            padding: 0 5px;
            color: red;
            font-style: normal;
        }

    </style>
</head>
<body>
<div class="page-content addAndUpdate">
    <form class="form-horizontal" id="postimage">
        <input type="hidden" value="$!banner.imageUrl" id="imageUrl" name="imageUrl"/>
        <input type="hidden" value="$!banner.pkId" id="pkId" name="pkId"/>
        <input type="hidden" value="$!showType" id="showType" name="showType"/>

        <input type="hidden" value="$!fileServiceUrl" name="ehfsimage" id="ehfsimage"/>
        <input type="hidden" value="$!content.contextPath" name="contextPath" id="contextPath">
        <input type="hidden" value="${ctxPath}" name="ctxPath" id="ctxPath">


        <div class="form-group">
            <label class="col-xs-3 control-label" ><span id="functionnametil" style="color: red">*</span>
                banner图：</label>
            <div class="phone col-xs-9 clearfix">

                <div class="form-control-static" style='cursor: pointer;'>
                    <input name="UploadBtn" type="file" id="fileupId2" onchange="upLoadImg(this,0)"/>
                    <div id="fileBtn"></div>
                </div>
                <span id="functionname" style="color: red">建议图片尺寸：890*270，建议图片大小：50-100kb</span>
            </div>
        </div>

        <div class="fileinput-new import-pic">
            <div class="text-center clearfix" id="imgDiv" style="width: 100%; height: 150px;  ">
                <div class="pull-left" style="display:none">
                    <img id="ladybug_ant" src="" width="100" height="100" alt=""/><br>
                    <label>删除</label>
                </div>

            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 text-right"><span style="color: red">*</span> 标题：</label>
            <div class="col-xs-8">
                <textarea id="title" name="title" class="form-control" placeholder="标题" rows="3" maxlength="300">$!banner.title</textarea>
            </div>
        </div>

        <!-- temp  下期功能
        <input type="radio" name="inType" value="1" checked="checked" hidden="hidden">超链接</input>-->
        <div class="form-group">
            <label class="col-xs-3" style="padding-top: 5px; text-align: right;"><span class="required"> * </span>描述类型：</label>
            <div class="col-xs-9">
                <label class="radio-inline">
                    <input type="radio" name="inType" value="1" #if($banner.inType !='2' ) checked="checked"
                           #end>超链接</input>
                </label>
                <label class="radio-inline" hidden="hidden">
                    <input type="radio" #if($banner.inType== '2') checked="checked" #end name="inType" value="2"
                    >文本</input>
                </label>
            </div>
        </div>

        <div class="form-group clearfix" id="editHtml" hidden="hidden">
            <label class="col-xs-3" style="padding-top: 5px; text-align: right;"><span style="color: red;"> * </span>描述内容：</label>
            <div class="col-xs-8">
                <textarea id="context" name="context" placeholder="描述内容" class="form-control">#if($banner.inType == '2')$!banner.context#end</textarea>
            </div>
        </div>
        <div class="form-group" id="editText">
            <label class="col-xs-3" style="padding-top: 5px; text-align: right;"><span class="required"> * </span>描述内容：</label>
            <div class="col-xs-8">
                <textarea name="link" id="link" placeholder="描述内容" rows="3" maxlength="300" class="form-control">#if($banner.inType == '1')$!banner.link#end</textarea>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-3 text-right">简介：</label>
            <div class="col-xs-8">
                <textarea id="content" name="content" class="form-control" placeholder="简介" rows="3" maxlength="300">$!banner.content</textarea>
            </div>
        </div>

        <div class="form-group" hidden="true">
            <label class="col-xs-3 text-right">排序：</label>
            <div class="col-xs-8">
                <textarea id="sort" name="sort" class="form-control" placeholder="排序" rows="3" maxlength="300">$!banner.sort</textarea>
            </div>
        </div>

        <div class="form-group" hidden="true">
            <label class="col-xs-3 text-right">创建时间：</label>
            <div class="col-xs-8">
                <textarea readonly="readonly" id="createTime" name="createTime" class="form-control" placeholder="创建时间"
                          rows="3" maxlength="300">$!banner.createTime</textarea>
            </div>
        </div>

        <div class="form-group text-center">
            <button type="button" class="btn btn-primary"
                    onclick="saveBanner()">保存
            </button>
            <button type="button" class="btn" onclick="cancle()">取消</button>

        </div>
    </form>
</div>
<script
        src="/resource/js/fileUpload/ajaxfileupload.js"></script>
</body>
</html>