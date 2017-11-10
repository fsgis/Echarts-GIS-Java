<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<head>
    <meta charset="utf-8">
    <title>OSM</title>
    <style type="text/css">
        body{
            margin: 0;
            overflow: hidden;
            background: #fff;
        }
        #map{
            position: relative;
            height: 553px;
            border:1px solid #3473b7;
            top: 10px;
        }
    </style>
    <link href='./css/bootstrap.min.css' rel='stylesheet' />
    <link href='./css/bootstrap-responsive.min.css' rel='stylesheet' />
    <script src = './libs/SuperMap.Include.js'></script>
     <script src = 'jquery.js'></script>
    <script type="text/javascript">
        var map, osmLayer,pointVector,vector,dataAdded=false;
        var contentHtml="";
        var contentHtmltext="";
        var ajaxdata = [];
        var ajaxdataval = [];
        
        function init(){
            map = new SuperMap.Map("map",{controls:[
                new SuperMap.Control.Navigation() ,
                new SuperMap.Control.Zoom(),
                new SuperMap.Control.LayerSwitcher(),
                new SuperMap.Control.MousePosition()
            ]});
            osmLayer = new SuperMap.Layer.OSM();
            vector = new SuperMap.Layer.Vector("vector");
            map.addLayers([osmLayer,vector]);
            addData();
            var bounds = new SuperMap.Bounds(-33554432,-33554432,33554432,33554432);
            map.zoomToExtent(bounds);
            //
            
            var callbacks={
                    click: function(currentFeature){
                    closeInfoWin();
                    
                    //AJAX获取name、num数据
                    
                   $.ajax({
        				type : 'post',	//传输类型
        				async : false,	//同步执行
        				url : 'bar.do',	//web.xml中注册的Servlet的url-pattern
        				data : {},
        				dataType : 'json', //返回数据形式为json
        				success : function(result) {
        					if (result) {
        						for (var i=0; i<result.length; i++) {
        							ajaxdata[i]=result[i].name;
        							ajaxdataval[i]=result[i].num;
        						    //alert(ajaxdata[i]);
        						}
        					}
        				},
        				error : function(errorMsg) {
        					alert("加载数据失败");
        				}
        			});
                    
                    //AJAX
        			
                    
                    //拼字符串
                    
        			for (var i=0; i<ajaxdata.length; i++){
        				
        				contentHtml+=ajaxdata[i]+" ";
        				contentHtmltext+=ajaxdataval[i]+" ";
        				
        				//alert(contentHtml);
        			}
        			contentHtml="Name："+contentHtml+"; "+"Num: "+contentHtmltext+" "+"<a href='index.jsp'>查看详情</a>";
                    
        			//
                    var popup = new SuperMap.Popup.FramedCloud("popwin",
                            new SuperMap.LonLat(0,0),
                            null,
                            contentHtml,
                            null,
                            true);
                    infowin = popup;
                    map.addPopup(popup);
                }
                };
                var  selectFeature = new SuperMap.Control.SelectFeature(vector,
                        {
                            callbacks: callbacks
                        });
                map.addControl(selectFeature);
                selectFeature.activate();
        }

        function addData(){
            if(!dataAdded)
            {
                vector.removeAllFeatures();
                //点对象
                var point= new SuperMap.Geometry.Point(0,0);
                var pointVector = new SuperMap.Feature.Vector(point);
                pointVector.style={
                    fillColor:"red",
                    strokeColor:"yellow",
                    pointRadius:10
                };

                vector.addFeatures([pointVector]);
                dataAdded=true;
            } else{
                alert("数据已加载。");
            }
        }
        
        
        var  infowin=null;

        function closeInfoWin(){
            if(infowin){
                try{
                    infowin.hide();
                    infowin.destroy();
                }
                catch(e){}
            }
        }
        
        
    </script>
</head>
<body onLoad = "init()">
<div id = "map"></div>

</body>
</html>