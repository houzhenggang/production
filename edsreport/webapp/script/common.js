/* 打开一个标签 */
var timer = "";
function loading(frameName){
	$.messager.progress({
		title:'请等待',
		msg:'页面加载中...'
	});
	timer = setInterval(function(){
    	 processListener(frameName);
    }, 100);
}

function processListener(frameName){
	if(window.frames[frameName].document.readyState == "complete"){
		$.messager.progress("close");
		clearInterval(timer);
	}
}
/* 生成标签内容 */
function createTabContent(frameName){
    return '<iframe name="'+ frameName +'" style="width:100%;height:100%;" scrolling="auto" frameborder="0"></iframe>';
}
function OpenTab(title, url){
    /**
    如果这个标题的标签存在，则选择该标签
    否则添加一个标签到标签组
    */
    if($("#tabs").tabs('exists', title)){
        $("#tabs").tabs('select', title);
    }else{
        $("#tabs").tabs('add',{
            title: title,
            content : createTabContent(url),
            fit : true,
            closable: true,
            tools:[{
				iconCls:'icon-mini-refresh',
				handler:function(){
					window.frames[url].window.location.href = url;
					loading(url);
				}

			}]

        });
        window.frames[url].window.location.href = url;
        loading(url);
    }
}
function OpenWin(title, width, height, url){
    /**
    如果这个标题的标签存在，则选择该标签
    否则添加一个标签到标签组
    */
	$('#myWindows').dialog({  
	    title: title,  
	    width: width,  
	    height: height,  
	    closed: false,  
	    cache: false,  
	    content: createTabContent('myDialog'),
	    iconCls:'icon-edit',
	    modal: true  
	});
	window.frames['myDialog'].window.location.href = url;
    loading('myDialog');
}
/**
 * 关闭窗体
 * @param winName
 */
function CloseWin(winName){
	$('#'+winName).dialog('close');
}

$.messager.defaults={ok:"确 定",cancel:"取 消"};

