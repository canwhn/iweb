jQuery().ready(function(){
	$('#myForm').hide();
	//$('#userTable').append("<div>Hello UserList</div>");
	
	getAllUserList();
	//getAllUserListDemo1();
	//getAllUserListDemo1_1();
	//getAllUserListDemo2();
	
    $.fun2();

})

$.fn.extend({
    fun1 : function() {
            alert(1);
        }
});
//给$添加自定义方法
$.fun2 = function(){
	console.log('jQuery添加自定义函数fun2  可用$.fun2()进行调用');
};


$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

function getAllUserList(){
	var web_url = $('#web_url').val()+'user/getAllUserList';
	//可用赋值1
	var user = $('#userForm').serializeObject();
	//console.log('result1---'+user);
	
	//可用逻辑2
	//var user = {'id':'1','age':'1','userName':'af'};
	//console.log('result2---'+user.toString());//result2---{'id':'1','age':'1','userName':'af'}
	
    //不可用逻辑1
	//var tdata = {'id':'1','age':'1','userName':'af'};
    //var user  = JSON.stringify(tdata);
    //console.log('result3---'+user);//result3---{"id":"1","age":"1","userName":"af"}
	
	//不可用逻辑2
	//var user = $.toJSON($('#userForm').serializeObject());
	//console.log('result0---'+user);
	
	//不可用逻辑3
	//var array = [];
	//array.push({id:1,age:10,userName:'张三'});
	//var user = JSON.stringify(array);
	//console.log('result1---'+user);
	
	$.ajax({
        type : 'POST',
        //contentType : 'application/json',  //注释此行，可用逻辑1和可用逻辑2对应的后台参数user才能赋值
        url : web_url,
        data :user,
        dataType : 'json',
        success : function(result){
    		var data = result.list;
    		$('#data_table').remove();
    		if (data != null) {
    				var html = '<table id="data_table" class="table table-striped">';
    	            html = html + '<thead>';
    	            html = html +'<tr>';
    	            html = html +'<th>用户ID</th>';
    	            html = html +'<th>年龄</th>';
    	            html = html +'<th>用户名</th>';
    	            html = html +'<th>修改</th>';
    	            html = html +'<th>删除</th>';
    	            html = html +'</tr>';
    	            html = html +'</thead>';
    	            html = html +'<tbody>';	
    	            $.each(data, function (index, item) { //遍历返回的json
    		            html = html +'<tr>';
    		            html = html +'<td>' + item.id + '</td>';
    		            html = html +'<td>' + item.age + '</td>';
    		            html = html +'<td>' + item.userName + '</td>';
    		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">修改</button>' + '</td>';
    		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">删除</button>' + '</td>';
    		            html = html +'</tr>';
    	            });
    	            html = html +'</tbody>';
    	            html = html +'</table>';
    	            $("#list").append(html);
    	            
    	            
    	          var pageCount = 2; //取到pageCount的值(把返回数据转成object类型)
    	          var currentPage = 1; //得到urrentPage
    	          var options = {
    	            bootstrapMajorVersion: 3, //版本
    	            currentPage: currentPage, //当前页数
    	            totalPages: pageCount, //总页数
    	            itemTexts: function (type, page, current) {
    	              switch (type) {
    	                case "first":
    	                  return "首页";
    	                case "prev":
    	                  return "上一页";
    	                case "next":
    	                  return "下一页";
    	                case "last":
    	                  return "末页";
    	                case "page":
    	                  return page;
    	              }
    	            },//点击事件，用于通过Ajax来刷新整个list列表
    	            onPageClicked: function (event, originalEvent, type, page) {
    	            	$.get(web_url,function(result){
    	            		var data = result.list;
    	            		$('#data_table').remove();
    	                   if(data != null) {
    	                	    var html = '<table id="data_table" class="table table-striped">';
    		       	            html = html + '<thead>';
    		       	            html = html +'<tr>';
    		       	            html = html +'<th>用户ID</th>';
    		       	            html = html +'<th>年龄</th>';
    		       	            html = html +'<th>用户名</th>';
    		       	            html = html +'<th>修改</th>';
    		       	            html = html +'<th>删除</th>';
    		       	            html = html +'</tr>';
    		       	            html = html +'</thead>';
    		       	            html = html +'<tbody>';	
    		       	            $.each(data, function (index, item) { //遍历返回的json
    		       		            html = html +'<tr>';
    		       		            html = html +'<td>' + item.id + '</td>';
    		       		            html = html +'<td>' + item.age + '</td>';
    		       		            html = html +'<td>' + item.userName + '</td>';
    		       		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">修改</button>' + '</td>';
    		       		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">删除</button>' + '</td>';
    		       		            html = html +'</tr>';
    		       	            });
    		       	            html = html +'</tbody>';
    		       	            html = html +'</table>';
    		       	            $("#list").append(html);
    		                  }
    	                    });
    	                }
    	            }
    	          $('#example').bootstrapPaginator(options);
    	        }
    	},
        error : function(data) {
          alert("error")
        }
      });
}

function getAllUserListDemo1(){
	debugger;
	$('#myForm').show();
	var web_url = $('#web_url').val()+'user/getAllUserListDemoOne';
	//可用赋值1
	//var user = $('#userForm').serializeObject();
	var user = {'id':'1','age':'1','userName':'af'};
	$.ajaxSetup({   
	    contentType : 'application/json;charset=UTF-8'  
	});
	$.ajax({
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',  //注释此行，可用逻辑1和可用逻辑2对应的后台参数user才能赋值
        url : web_url,
        data :user,
        dataType : 'json',
        success : function(result){
        	console.log("getAllUserListDemo1 --- success")
    	},
        error : function(data) {
        	console.log("getAllUserListDemo1 --- error")
        }
      });
}

function getAllUserListDemo1_1(){
	var saveDataAry=[];  
    var data1={"userName":"test","age":"12"};  
    var data2={"userName":"ququ","age":"14"};  
    saveDataAry.push(data1);  
    saveDataAry.push(data2); 
	var users = JSON.stringify(saveDataAry);
	var web_url = $('#web_url').val()+'user/getAllUserListDemoTwo';
	//可用赋值1
	var user = $('#userForm').serializeObject();
	$.ajax({
        type : 'POST',
        contentType : 'application/json',  //注释此行，可用逻辑1和可用逻辑2对应的后台参数user才能赋值
        url : web_url,
        data:users, 
        dataType : 'json',
        success : function(result){
        	console.log("getAllUserListDemo1 --- success")
    	},
        error : function(data) {
        	console.log("getAllUserListDemo1 --- error")
        }
      });
}


function getAllUserListDemo2(){
	$('#myForm').show();
	var web_url = $('#web_url').val()+'user/getAllUserListDemoThree';
	//可用赋值1
	var user = $('#userForm').serializeObject();
	$.ajax({
        type : 'POST',
        //contentType : 'application/json',  //注释此行，可用逻辑1和可用逻辑2对应的后台参数user才能赋值
        url : web_url,
        data :user,
        dataType : 'json',
        success : function(result){
        	console.log("getAllUserListDemo1 --- success")
    	},
        error : function(data) {
        	console.log("getAllUserListDemo1 --- error")
        }
      });

	/*
	$.post(web_url,param,function(result){
		var data = result.list;
		$('#data_table').remove();
		if (data != null) {
				var html = '<table id="data_table" class="table table-striped">';
	            html = html + '<thead>';
	            html = html +'<tr>';
	            html = html +'<th>用户ID</th>';
	            html = html +'<th>年龄</th>';
	            html = html +'<th>用户名</th>';
	            html = html +'<th>修改</th>';
	            html = html +'<th>删除</th>';
	            html = html +'</tr>';
	            html = html +'</thead>';
	            html = html +'<tbody>';	
	            $.each(data, function (index, item) { //遍历返回的json
		            html = html +'<tr>';
		            html = html +'<td>' + item.id + '</td>';
		            html = html +'<td>' + item.age + '</td>';
		            html = html +'<td>' + item.userName + '</td>';
		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">修改</button>' + '</td>';
		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">删除</button>' + '</td>';
		            html = html +'</tr>';
	            });
	            html = html +'</tbody>';
	            html = html +'</table>';
	            $("#list").append(html);
	            
	            
	          var pageCount = 2; //取到pageCount的值(把返回数据转成object类型)
	          var currentPage = 1; //得到urrentPage
	          var options = {
	            bootstrapMajorVersion: 3, //版本
	            currentPage: currentPage, //当前页数
	            totalPages: pageCount, //总页数
	            itemTexts: function (type, page, current) {
	              switch (type) {
	                case "first":
	                  return "首页";
	                case "prev":
	                  return "上一页";
	                case "next":
	                  return "下一页";
	                case "last":
	                  return "末页";
	                case "page":
	                  return page;
	              }
	            },//点击事件，用于通过Ajax来刷新整个list列表
	            onPageClicked: function (event, originalEvent, type, page) {
	            	$.get(web_url,function(result){
	            		var data = result.list;
	            		$('#data_table').remove();
	                   if(data != null) {
	                	    var html = '<table id="data_table" class="table table-striped">';
		       	            html = html + '<thead>';
		       	            html = html +'<tr>';
		       	            html = html +'<th>用户ID</th>';
		       	            html = html +'<th>年龄</th>';
		       	            html = html +'<th>用户名</th>';
		       	            html = html +'<th>修改</th>';
		       	            html = html +'<th>删除</th>';
		       	            html = html +'</tr>';
		       	            html = html +'</thead>';
		       	            html = html +'<tbody>';	
		       	            $.each(data, function (index, item) { //遍历返回的json
		       		            html = html +'<tr>';
		       		            html = html +'<td>' + item.id + '</td>';
		       		            html = html +'<td>' + item.age + '</td>';
		       		            html = html +'<td>' + item.userName + '</td>';
		       		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">修改</button>' + '</td>';
		       		            html = html +'<td>' + '<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">删除</button>' + '</td>';
		       		            html = html +'</tr>';
		       	            });
		       	            html = html +'</tbody>';
		       	            html = html +'</table>';
		       	            $("#list").append(html);
		                  }
	                    });
	                }
	            }
	          $('#example').bootstrapPaginator(options);
	        }
	});
	*/
}