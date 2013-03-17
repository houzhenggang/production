$.extend($.fn.validatebox.defaults.rules, {   
	minlength: {   
        validator: function (value, param) {
        	if(value != ""){
        		if(value.length < param){
        			return false;
        		}
        	}
            return true;   
        },   
        message: '长度必须不能小于5'   
    },
	
	loginName: {   
        validator: function (value, param) {   
            return /^[\u0391-\uFFE5\w]+$/.test(value);   
        },   
        message: '登录名称只允许汉字、英文字母、数字及下划线。'   
    },
    safepass: {   
        validator: function (value, param) {   
            return (value.length >= 6);   
        },   
        message: '密码不能少于6位'   
    },   
    equalTo: {   
        validator: function (value, param) {
            return value == $("#"+param).val();   
        },   
        message: '两次输入的密码不一致'   
    },
    eaqualToOld : {
    	validator: function (value, param) {
    		var result = false;
    		$.ajax({
				type : 'post',
				url : param+'/security/management/password/checkPassword.action?oldPassword='+value,
				dataType : 'json',
				async : false,
				success : function(data){
					if(data == "1"){
						result = true;
					}else{
						result = false;
					}
				}
			});
            return result;   
        },   
        message: '原密码不正确' 
    },
    checkUsername : {
    	validator: function (value, param) {
    		var result = true;
    		if($("#userId").val() == ""){
    			$.ajax({
    				type : 'post',
    				url : param+'/security/management/user/checkUsername.action?username='+value,
    				dataType : 'json',
    				async : false,
    				success : function(data){
    					if(data == "1"){
    						result = true;
    					}else{
    						result = false;
    					}
    				}
    			});
    		}
            return result;   
        },   
        message: '用户名已存在' 
    },
    isExist : {
    	validator: function (value, param) {
    		var params = param.split("-");
    		var result = true;
    		if(value != params[1]){
	    		var stationId = $('#stationId').combobox('getValue');
				if(stationId != ""){
		    		$.ajax({
						type : 'post',
						url : params[0]+"/security/plant/checkExsit.action",
						data: "plantMonitorDate="+value+"&stationId="+stationId,
						dataType : 'json',
						async : false,
						success : function(data){
							if(data == "1"){
								result = true;
							}else{
								result = false;
							}
						}
					});
				}
    		}
            return result;   
        },   
        message: '该月份数据已存在' 
    },
    constantIsExist : {
    	validator: function (value, param) {
    		var result = true;
    		var nowStationId = $("#changeFlag").val();
    		var stationId = $('#stationId').combobox('getValue');
    		if(stationId != nowStationId){
				if(stationId != ""){
		    		$.ajax({
						type : 'post',
						url : param+"/security/soilanalysis/checkExsit.action",
						data: "stationId="+stationId,
						dataType : 'json',
						async : false,
						success : function(data){
							if(data == "1"){
								result = true;
							}else{
								result = false;
							}
						}
					});
				}
    		}
            return result;   
        },   
        message: '站点常量已存在' 
    },
    checkReportIsExist : {
    	validator: function (value, param) {
    		var result = true;
    		var reportStationId = $("#reportStationId").val();
    		var reportStartDate = $("#reportStartDate").val();
    		var stationId = $('#stationId').combobox('getValue');
    		var soilAnalysisDate = $("#operationTime").val();
    		if((stationId != reportStationId) || (reportStartDate != soilAnalysisDate)){
				if(stationId != ""){
		    		$.ajax({
						type : 'post',
						url : param+"/security/soilanalysis/checkReportExsit.action",
						data: "reportStationId="+stationId+"&reportStartDate="+soilAnalysisDate,
						dataType : 'json',
						async : false,
						success : function(data){
							if(data == "1"){
								result = true;
							}else{
								result = false;
							}
						}
					});
				}
    		}
            return result;   
        },   
        message: '数据已存在' 
    },
    checkStationExist : {
    	validator : function(value, param){
    		var result = true;
    		var nowStationId = $("#isAdd").val();
    		if(nowStationId != value){
    			if(value != ""){
    				$.ajax({
						type : 'post',
						url : param+"/security/management/station/checkStationExist.action",
						data: "stationId="+value,
						dataType : 'json',
						async : false,
						success : function(data){
							if(data == "1"){
								result = true;
							}else{
								result = false;
							}
						}
					});
    			}
    		}
    		return result;
    	},
    	message:"站号已存在"
    },
    changeView : {
    	validator : function(value, param){
    		if(value == "无"){
    			$("input", $("#"+param)).attr("disabled", true);
    			$("#"+param).css("display", "none");
    		}else{
    			$("input", $("#"+param)).attr("disabled", false);
    			$("#"+param).css("display", "");
    		}
	    	return true;
    	},
    	message:""
    }
});