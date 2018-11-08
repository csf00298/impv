//复选框选择事件
jQuery().ready(function () {
    $("#checkAll").bind("click", function () {
        if (this.checked) {
            $('input[name="aspInfoCheckbox"]').prop("checked", this.checked);
        } else {
            $('input[name="aspInfoCheckbox"]').removeProp("checked");
        }

    });

    //redio选择
    editMeth();
    showCont();
    $("input[name=inType]").click(function () {
        showCont();
    });
});

$(function () {
    $("#imgDiv").delegate("label", "click", function () {
        $("#imageUrl").val("");
        var parentimgsrc = $(this).parent().find("img").attr("src").replace($("#ehfsimage").val(), "");
        var imge = $("#imageUrl").val();
        $("#imageUrl").val(imge.replace(parentimgsrc, ""));
        $(this).parent().remove();
    })

    var imge = $("#imageUrl").val();
    if (imge != "") {
        setImg($("#imgDiv"), imge);
    }
})

function setImg(obj, str) {
    var array = str.split("\,");
    $.each(array, function (i, j) {
        if (j != "") {
            j = $("#ehfsimage").val() + "/" + j
            var imgtemp = "<img id='ladybug_ant' src='" + j + "' width='100' height='100' alt='' /><br/><label class='deleteimage'>删除</label>";
            obj.append("	<div class='pull-left' >" + imgtemp + "</div>");
        }

    })

}

function showCont() {
    //1为 超链接，2为文本
    switch ($("input[name=inType]:checked").val()) {
        case "1":
//   $("#context").val('');
//   KindEditor.instances[0].html('');
//   KE.html('context','');
//   editor.html("");
//   debugger;
//	$('.ke-edit-iframe').contentWindow.document.body.innerHTML = '';
            $("#editHtml").hide();
            $("#editText").show();

            break;
        case "2":
            $("#editText").hide();
            $("#link").val('');
            $("#editHtml").show();
            break;
        default:
            break;
    }
}

function editMeth() {
    /** 富文本编辑器 */
    KindEditor.ready(function (K) {
        editor = K.create('textarea[name="context"]', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            allowImageUpload: true,
            allowImageRemote: false,
            uploadJson: "upload",
            items: [
                'fontname', 'fontsize', '|',
                'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'removeformat', '|',
                'justifyleft', 'justifycenter', 'justifyright', '|',
                'emoticons', 'image'],
            width: "100%",
            height: "300px",
            minWidth: "100%",
            top: "50px",
            afterCreate: function () {
                var self = this;
                K.ctrl(document, 13, function () {
                    formSubmit();
                });
                K.ctrl(self.edit.doc, 13, function () {
                    formSubmit();
                });
            }
        });
    });

};


// 机构
function saveBanner() {
    // 检测数据是否合法
    if (!checkData()) {
        return false;
    }

    editor.sync();
    var type = $("input[name=inType]:checked").val();


    var dataVal = jQuery("#postimage").formSerialize();

    jQuery.ajax({
        type: "POST",
        url: jQuery("#contextPath").val() + "/banner/save",
        data: dataVal,
        success: function (retVal) {
            if (retVal.state != 200) {
                layer.alert(retVal.data, {
                    shade: false
                })
            } else {
                layer.alert('操作成功', {
                    shade: false
                    // 不显示遮罩
                }, function (e) {
                    //刷新父窗口
                    parent.treeListInstance.init();
                    top.$(".layui-layer-close").click();
                });
            }
        }
    });
}


// 检测数据是否合法
function checkData() {
    if (jQuery("#imageUrl").val().trim() == "") {
        layer.confirm('banner图不能为空', {
            btn: ['确定'], //按钮
            shade: false, //不显示遮罩
            offset: ['180px', '40%']
        });
        return false;
    }
    if ($(".pull-left").find("img").length >= 3) {
        layer.confirm('banner图每次只能上传一个', {
            btn: ['确定'], //按钮
            shade: false, //不显示遮罩
            offset: ['180px', '40%']
        });
        return false;
    }

    if (jQuery("#title").val().trim() == "") {
        layer.confirm('标题不能为空', {
            btn: ['确定'], //按钮
            shade: false, //不显示遮罩
            offset: ['180px', '40%']
        });
        return false;
    }

    var type = $("input[name=inType]:checked").val();
    if ("1" == type) {
        var tempContent = jQuery("#link").val().trim();
        if (tempContent == "") {
            layer.confirm('超链接内容不能为空！', {
                btn: ['确定'], //按钮
                shade: false, //不显示遮罩
                offset: ['180px', '40%']
            });
            return false;
        } else {
            if (tempContent.length > 400) {
                layer.confirm('模板内容不能超过400！', {
                    btn: ['确定'], //按钮
                    shade: false, //不显示遮罩
                    offset: ['180px', '40%']
                });
                return false;
            }
        }
    } else {
        //2为文本编辑类型
//		alert(editor.html());
//		if(jQuery("#context").val().trim()==""){		
        if (editor.html().trim() == "") {
            layer.confirm('文本内容不能为空！', {
                btn: ['确定'],
                shade: [0.3, '#393D49'],
                shadeClose: false,
                offset: ['180px', '40%']
            });
            return false;
        }
    }

    /*	//修改页面 图片个数判断
     if($('#layui-layer-iframe2')[0].contentWindow.$(".pull-left").find("img").length>=3){
     layer.confirm('banner图每次只能上传一个', {
     btn: ['确定'], //按钮
     shade: false, //不显示遮罩
     offset : ['180px' , '40%']
     });
     return false;
     }*/
    return true;
}

function cancle() {
    top.$(".layui-layer-close").click();
}


function upClick(obj) {
    var targObj = obj.find('input:file');
    document.getElementById(targObj.attr("id")).click();

}

function upLoadImg(path, state) {
    var uploadFlag = 0;
    var filepath = path.value;
    if (filepath == "") {
        alert("请选择上传的文件！");
        return;
    }

    var falg = imageTypeVerify(path);
    if (!falg) {
        alert("只能上传bmp,jpeg,jpg,gif,png格式的图片！");
        return;
    }

    /**
     * 图片上传格式校验
     */
    function imageTypeVerify(path){
        var filepath = path.value;
        var extname = filepath.substring(filepath.lastIndexOf(".") + 1,
            filepath.length);
        extname = extname.toLowerCase();// 处理了大小写
        if (extname != "jpeg" && extname != "jpg" && extname != "gif" && extname != "png"&& extname != "bmp") {
            return false ;
        }else{
            return true;
        }
    }

    var fileupId = path.id;
    $.ajaxFileUpload({
        url: $("#ctxPath").val() + '/fileupload/savePicture?dt=' + new Date().getTime(),
        secureuri: false,
        type: "POST",
        fileElementId: fileupId,
        data: {"cz": "", "size": "", "imageBPath": "banner"},
        dataType: "json",
        success: function (returnData) {
            if (returnData.flag == true) {
                var imgSrcPath = returnData.fileUploadPath + returnData.imgName;
                var imgtemp = "<img id='ladybug_ant' src='" + imgSrcPath + "' width='100' height='100' alt='' /><br/><label class='deleteimage'>删除</label>";
                $("#imgDiv").append("	<div class='pull-left' >" + imgtemp + "</div>");
                var updateimg = $("#imageUrl").val();
                if (updateimg != "") {
                    var upldateimage = updateimg += returnData.imgName;
                    $("#imageUrl").val(upldateimage);
                }
                else {
                    var upldateimage = updateimg += returnData.imgName;
                    $("#imageUrl").val(upldateimage);
                }
            } else {
                alert(returnData.message);
            }
        },
        error: function (a, b, c) {
            alert("上传失败！");
        }
    })
}


function closeWindow() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}

/*$(function () {
    $("#imgBtn").uploadFile({
        url: "file.php",
        fileSuffixs: ["jpg", "png", "gif"],
        maximumFilesUpload: 7,//最大文件上传数
        onAllComplete: function () {
            alert("全部上传完成");
        },
        isGetFileSize: true,//是否获取上传文件大小，设置此项为true时，将在onChosen回调中返回文件fileSize和获取大小时的错误提示文本errorText
        onChosen: function (file, obj, fileSize, errorText) {
            if (!errorText) {

            } else {
                alert(errorText);
                return false;
            }
            return true;//返回false将取消当前选择的文件
        },
        perviewElementId: "fileList", //设置预览图片的元素id
        perviewImgStyle: {width: '350px', height: '250px', border: '1px solid #ebebeb'}//设置预览图片的样式
    });
})*/
