// JavaScript Document
$(document).ready(function() {
		
		/*Initialisation of variables, tables and objects*/
		
		var theCanvas = [];
		var ctx = [];
		var canvasName = [];
		theCanvas[0] = document.getElementById('Layer1');
		ctx[0] = theCanvas[0].getContext('2d');
		var theVideo = document.getElementById('firstVideo');
		
		var w, h, ratio;
		
		var points = {};
		
		points.rect = []; points.line = []; points.point = []; points.circle = []; points.oval = []; points.text = []; points.frameRate = 0;
		
		//points.rect.name = {}; points.line.name = {}; points.point.name = {}; points.circle.name = {}; points.oval.name = {};
		//points.rect.x1=[]; points.rect.y1=[]; points.rect.x2=[]; points.rect.y2=[];
		//points.line.x1=[]; points.line.y1=[]; points.line.x2=[]; points.line.y2=[];
		//points.point.x1=[]; points.point.y1=[];
		//points.circle.r=[]; points.circle.x1=[]; points.circle.y1=[]; 
		//points.oval.x1=[]; points.oval.y1=[]; points.oval.width=[]; points.oval.height=[];
		 
		var jsonObj;
		var numCanvas=0; var numFrames=0;
		
		// calling of ajax function		
		$.ajax({
			type: "GET",
			dataType: "json",
			url: "http://portfolionejcj.herokuapp.com/diplomska/json/bicycle2.json",
			success: function(data){

					/* Funkcije, kjer se shranjujejo podatki iz JSONA */
					jsonObj = data;
					
					var enumRect=0, enumText=0, enumLine=0, enumPoint=0, enumCircle=0, enumOval=0;
					
					/* going through each layer*/
					$.each(jsonObj.layers, function(i, obj){														
							var num = i + parseInt(2);
							canvasName[i] = {"layer" : jsonObj.layers[i].label, "name":jsonObj.layers[i].name, "type": jsonObj.layers[i].type};
							
							if(canvasName[i].type == "rect")
							{
									points.rect[enumRect] = {"name":canvasName[i].name, "x1":[], "x2":[], "y1":[], "y2":[]};
									enumRect++;
							}
							if(canvasName[i].type == "text")
							{
									points.text[enumText] = {"name":canvasName[i].name, "x1":[], "y1":[], "text":[], "length":0};
									enumText++;
							}
							if(canvasName[i].type == "line")
							{
									points.line[enumLine] = {"name":canvasName[i].name, "x1":[], "x2":[], "y1":[], "y2":[]};
									enumLine++;
							}
							if(canvasName[i].type == "point")
							{
									points.point[enumPoint] = {"name":canvasName[i].name, "x1":[], "y1":[]};
									enumPoint++;
							}
							if(canvasName[i].type == "circle")
							{
									points.circle[enumCircle] = {"name":canvasName[i].name, "x1":[], "y1":[], "r":[]};
									enumCircle++;
							}
							if(canvasName[i].type == "oval")
							{
									points.oval[enumOval] = {"name":canvasName[i].name, "x1":[], "y1":[], "height":[], "width":[]};
									enumOval++;
							}
							
							/*adding layers of different annotations*/
							$(".canvasPlayer").append("<canvas id='"+canvasName[i].layer+"' class='show' style='position:absolute; z-index:"+num+";'></canvas>");
							/*adding appearance buttons of different annotations*/
							$("#buttons").append("<li><button id='LayerButton"+(parseInt(i)+parseInt(1))+"' title = '"+canvasName[i].layer+"'>Hide</button>Layer: "+canvasName[i].layer+"</li>")
							theCanvas[i+parseInt(1)] = document.getElementById(canvasName[i].layer);
							ctx[i+parseInt(1)] = theCanvas[i+parseInt(1)].getContext("2d");
							numCanvas++;
						});
						
						var num = numCanvas + parseInt(2);
						canvasName[numCanvas] = {"layer" : "default"};
						$(".canvasPlayer").append("<canvas id='default' style='position:absolute; z-index:"+num+";'></canvas>");
						theCanvas[numCanvas+parseInt(1)] = document.getElementById("default");
						ctx[numCanvas+parseInt(1)] = theCanvas[numCanvas+parseInt(1)].getContext("2d");
						
						
					points.frameRate = jsonObj.frameRate;
					
					$.each(jsonObj.sequence, function(i, obj){
						var numRect = []; var numLine = []; var numPoint = []; var numCircle = []; var numOval = []; var numText = [];				
						
						$.each(jsonObj.sequence[i], function(j, obj){
														
							for(k in points.rect)
							{
								if(numRect[k]!=0)
									numRect[k]=0;
								if(jsonObj.sequence[i][j].type == "rect" && jsonObj.sequence[i][j].layer == points.rect[k].name){
   									points.rect[k].x1.push({"x1":jsonObj.sequence[i][j].x, "layer" : jsonObj.sequence[i][j].layer});
									points.rect[k].y1.push({"y1":jsonObj.sequence[i][j].y, "layer" : jsonObj.sequence[i][j].layer});
									points.rect[k].x2.push({"x2":jsonObj.sequence[i][j].width, "layer" : jsonObj.sequence[i][j].layer});
									points.rect[k].y2.push({"y2":jsonObj.sequence[i][j].height, "layer" : jsonObj.sequence[i][j].layer});
									//numRect[k]=1;
								}
								else
								{
									numRect[k]++;	
								}
							}
							
							for(k in points.text)
							{
								if(jsonObj.sequence[i][j].type == "text" && jsonObj.sequence[i][j].layer == points.text[k].name){
   									points.text[k].x1.push({"x1":jsonObj.sequence[i][j].x, "layer" : jsonObj.sequence[i][j].layer});
									points.text[k].y1.push({"y1":jsonObj.sequence[i][j].y, "layer" : jsonObj.sequence[i][j].layer});
									points.text[k].text.push({"text":jsonObj.sequence[i][j].text, "layer" : jsonObj.sequence[i][j].layer});
									points.text[k].length.push({"length":jsonObj.sequence[i][j].length, "layer" : jsonObj.sequence[i][j].layer});
									numText[k]=1;
								}
							}

							
							for(k in points.line)
							{
								if(jsonObj.sequence[i][j].type == "line" && jsonObj.sequence[i][j].layer == points.line[k].name){
   									points.line[k].x1.push({"x1" : jsonObj.sequence[i][j].x, "layer" : jsonObj.sequence[i][j].layer});
									points.line[k].y1.push({"y1" : jsonObj.sequence[i][j].y, "layer" : jsonObj.sequence[i][j].layer});
									points.line[k].x2.push({"x2" : jsonObj.sequence[i][j].width, "layer" : jsonObj.sequence[i][j].layer});
									points.line[k].y2.push({"y2" : jsonObj.sequence[i][j].height, "layer" : jsonObj.sequence[i][j].layer});	
									numLine[k]=1;							
								}
							}

							for(k in points.point)
							{
								if(jsonObj.sequence[i][j].type == "point" && jsonObj.sequence[i][j].layer == points.point[k].name){
   									points.point[k].x1.push({"x1" : jsonObj.sequence[i][j].x, "layer" : jsonObj.sequence[i][j].layer});
									points.point[k].y1.push({"y1" : jsonObj.sequence[i][j].y, "layer" : jsonObj.sequence[i][j].layer});
									numPoint[k]=1;
								}							
							}

							for(k in points.circle)
							{
								if(jsonObj.sequence[i][j].type == "circle" && jsonObj.sequence[i][j].layer == points.circle[k].name){							
   									points.circle[k].r.push({"r" : jsonObj.sequence[i][j].radius, "layer" : jsonObj.sequence[i][j].layer});
									points.circle[k].x1.push({"x1" : jsonObj.sequence[i][j].x, "layer" : jsonObj.sequence[i][j].layer});
									points.circle[k].y1.push({"y1" : jsonObj.sequence[i][j].y, "layer" : jsonObj.sequence[i][j].layer});
									numCircle[k]=1;				
								}
							}

							for(k in points.circle)
							{
								if(jsonObj.sequence[i][j].type == "oval" && jsonObj.sequence[i][j].layer == points.oval[k].name){
									points.oval[k].x1.push({"x1" : jsonObj.sequence[i][j].x, "layer" : jsonObj.sequence[i][j].layer});
									points.oval[k].y1.push({"y1" : jsonObj.sequence[i][j].y, "layer" : jsonObj.sequence[i][j].layer});
									points.oval[k].height.push({"height" : jsonObj.sequence[i][j].height, 
																"layer" : jsonObj.sequence[i][j].layer});
									points.oval[k].width.push({"width" : jsonObj.sequence[i][j].width, "layer" : jsonObj.sequence[i][j].layer});
									numOval[k]=1;					
								}
							}
							
						});
						
						for(j in numRect){
							if(numRect[j] == points.rect.length)
							{
								points.rect[j].x1.push({"x1" : 0, "layer" : points.rect[j].name});
								points.rect[j].y1.push({"y1" : 0, "layer" : points.rect[j].name});
								points.rect[j].x2.push({"x2" : 0, "layer" : points.rect[j].name});
								points.rect[j].y2.push({"y2" : 0, "layer" : points.rect[j].name});	
							}
						}
						for(j in numText){
							if(numText[j] == points.text.length)
							{
								points.text[j].x1.push({"x1" : 0, "layer" : points.text[j].name});
								points.text[j].y1.push({"y1" : 0, "layer" : points.text[j].name});
								points.text[j].text.push({"text" : "", "layer" : points.text[j].name});
								points.text[j].length.push({"length" : 0, "layer" : points.text[j].name});	
							}
						}
						
						for(j in numLine){
							if(numLine[j] == 0)
							{
								points.line[i].x1.push({"x1" : 0, "layer" : "default"});
								points.line[i].y1.push({"y1" : 0, "layer" : "default"});
								points.line[i].x2.push({"x2" : 0, "layer" : "default"});
								points.line[i].y2.push({"y2" : 0, "layer" : "default"});
							}
						}
						for(j in numPoint){
							if(numPoint[j] == 0)
							{
								points.point[i].x1.push({"x1" : 0, "layer" : "default"});
								points.point[i].y1.push({"y1" : 0, "layer" : "default"});	
							}
						}
						for(j in numCircle){
							if(numCircle[j] == 0)
							{
								points.circle[i].r.push({"r" : 0, "layer" : "default"});
								points.circle[i].x1.push({"x1" : 0, "layer" : "default"});
								points.circle[i].y1.push({"y1" : 0, "layer" : "default"});
							}
						}
						for(j in numOval){
							if(numOval[j] == 0)
							{
								points.oval[i].x1.push({"x1" : 0, "layer" : "default"});
								points.oval[i].y1.push({"y1" : 0, "layer" : "default"});
								points.oval[i].height.push({"height" : 0, "layer" : "default"});
								points.oval.width.push({"width" : 0, "layer" : "default"});
							}
						}								
						numFrames++;
					});			
				}
			});
		
		/* Funkcije, kjer se risejo stvari */
		
		// Tu se video narise v canvasu
		var snap = function() {
			ctx[0].drawImage(theVideo, 0, 0, w, h);
			$("#counter").html("Frame: "+videoTime());	
		};		
		
		// Tu se brise canvas, da se lik lahko po videu premika brez sledi
		var blank = function(i) {
			ctx[i].clearRect(0,0,ctx[i].canvas.width, ctx[i].canvas.height);				
		};
		
		// ugotavljamo, kateri na kateri layer moramo risati
		
		var numLayer = function(shapeNum ,frameNum, vector){
			if(vector == "rect"){
				for(i in canvasName){
					if(points.rect[shapeNum].x1[frameNum].layer == canvasName[i].name)
						return parseInt(i);
					if(points.rect[shapeNum].x1[frameNum].layer == "default")
						return parseInt(canvasName.length-1);
				}
			}
			if(vector == "point"){
				for(i in canvasName){
					if(points.point[shapeNum].x1[frameNum].layer == canvasName[i].name)
						return parseInt(i);
					if(points.point[shapeNum].x1[frameNum].layer == "default")
						return parseInt(canvasName.length-1);
				}
			}
			if(vector == "line"){
				for(j in points.line){
					if(points.line[shapeNum].x1[frameNum].layer == canvasName[i].name)
						return parseInt(i);
					if(points.line[shapeNum].x1[frameNum].layer == "default")
						return parseInt(canvasName.length-1);
				}
			}
			if(vector == "circle"){
				for(j in points.circle){
					if(points.circle[shapeNum].x1[frameNum].layer == canvasName[i].name)
						return parseInt(i);
					if(points.circle[shapeNum].x1[frameNum].layer == "default")
						return parseInt(canvasName.length-1);
				}
			}
			if(vector == "oval"){
				for(i in canvasName){
					if(points.oval[shapeNum].x1[frameNum].layer == canvasName[i].name)
						return parseInt(i);	
					if(points.oval[shapeNum].x1[frameNum].layer == "default")
						return parseInt(canvasName.length-1);
				}
			}
			if(vector == "text"){
				for(i in canvasName){
					if(points.text[shapeNum].x1[frameNum].layer == canvasName[i].name)
						return parseInt(i);
					if(points.text[shapeNum].x1[frameNum].layer == "default")
						return parseInt(canvasName.length-1);
				}
			}
		};
		
		var videoTime = function(){
			return Math.floor((theVideo.currentTime*points.frameRate).toPrecision(3))	
		}
		
/*Funkcije, kjer risemo like na canvas*/

		var a=[]; var addedRect = {};
		var rect = function(){			
			for(i in points.rect){
				
				var x1, x2, y1, y2;
				var noLayer = 1;
				
				if(a[i] == null)
					a[i] = 0;
				if(a[i] == points.rect[i].x1.length)
					a[i]--;
			
				if(a[i] < points.rect[i].x1.length)
					noLayer+=numLayer(i,a[i],"rect");
			
				if(!theVideo.currentTime == 0 && !theVideo.paused)
				{			
					x1 = points.rect[i].x1[a[i]].x1;
					x2 = points.rect[i].x2[a[i]].x2;
					y1 = points.rect[i].y1[a[i]].y1;
					y2 = points.rect[i].y2[a[i]].y2;
				}
				if(theVideo.currentTime == 0 || theVideo.ended)
				{
					x1=-1000; y1=-1000;
					a[i]=0;
					blank(noLayer);	 
				}
			
				if(theVideo.currentTime > 0 && theVideo.paused){
					a[i] = videoTime();
				}
			
				if(theVideo.currentTime > 0 && a[i] < points.rect[i].x1.length && !theVideo.paused){
					a[i] = videoTime();
					blank(noLayer);
				}
									
			
				if(noLayer == 1)
					ctx[noLayer].strokeStyle = 'black';
				if(noLayer == 2)
					ctx[noLayer].strokeStyle = 'red';
				if(noLayer == 3)
					ctx[noLayer].strokeStyle = 'green';

				ctx[noLayer].lineWidth = 1.5;
				ctx[noLayer].strokeRect(x1,y1,x2,y2);	
			}
			
			//addedRect = {"noLayer": noLayer, "x": x1, "y":y1, "width":x2, "height":y2 };		
			
		};
		
		function writeMessage(canvas, ctx, message) {
        	
        	var x1 = canvas.width*(0.25 + 0.155);
			var y1 = canvas.height*(0.75 + 0.145);
			ctx.font = '12pt Calibri';
        	ctx.fillStyle = 'black';
        	ctx.fillText(message, x1, y1);
		}
		
		function drawRect(canvas, ctx){
			var x1 = canvas.width*0.25;
			var y1 = canvas.height*0.75;
			var height = canvas.height*0.25;
			var width = canvas.width*0.5;
			ctx.fillStyle = "rgba(255, 0, 0, 0.2)";
			ctx.fillRect(x1,y1,width,height);	
		}
		
		function getMousePos(canvas, evt) {
        	var rect = canvas.getBoundingClientRect();
        	return {
          		x: evt.clientX - rect.left,
          		y: evt.clientY - rect.top
        	};
      	}
		
		var triggerText = 0;
		var textShow = function() {
			theCanvas[addedRect.noLayer+1].addEventListener('mousemove', function(evt) {
          		var mousePos = getMousePos(theCanvas[addedRect.noLayer], evt);
          		var message = 'Click me';
          		if(mousePos.x>=addedRect.x && mousePos.x<=(addedRect.x+addedRect.width) 
				   && mousePos.y>=addedRect.y && mousePos.y<=(addedRect.y+addedRect.height))
				{
					drawRect(theCanvas[addedRect.noLayer+1], ctx[addedRect.noLayer+1]);
					writeMessage(theCanvas[addedRect.noLayer+1], ctx[addedRect.noLayer+1], message);
					triggerText = 1;
					window.open('http:\\www.rtvslo.si');
				}
				else
					triggerText = 0;
				
				theCanvas[addedRect.noLayer+1].addEventListener('mousedown', function(evt) {
					var mousePos2 = getMousePos(theCanvas[addedRect.noLayer+1], evt);
					if(mousePos2.x>=theCanvas[addedRect.noLayer+1].width*0.25 && mousePos2.x<=theCanvas[addedRect.noLayer+1].width*0.75 &&
						mousePos2.y>=theCanvas[addedRect.noLayer+1].height*0.75 && mousePos2.y<=theCanvas[addedRect.noLayer+1].height)
					{
    					window.open('http:\\www.rtvslo.si');
					}
				}, false);
        	}, false);		
		}
		
		
//		theVideo.addEventListener('play', function(){
//			theCanvas[addedRect.noLayer+1].addEventListener('mousedown', function(evt) {
//				var mousePos = getMousePos(theCanvas[addedRect.noLayer+1], evt);
//				if(mousePos.x>=theCanvas[addedRect.noLayer+1].width*0.25 && mousePos.x<=theCanvas[addedRect.noLayer+1].width*0.75 &&
//					mousePos.y>=theCanvas[addedRect.noLayer+1].height*0.75 && mousePos.y<=theCanvas[addedRect.noLayer+1].height)
//				{
//					$("#"+canvasName[addedRect.noLayer+1]).click(function(e){
//    					window.open('www.rtvslo.si');
//						return false;
// 					});
//				}
//			}, false);
//		},false);
		  
		// ostali liki
		var b=0;
  		var line = function(){
			
			var x1,x2,y1,y2;		  
			
			var noLayer=numLayer(parseInt(b), "line");
			
			if(!theVideo.currentTime == 0 && !theVideo.paused)
			{			
				x1 = points.line.x1[b].x1;
		  		x2 = points.line.x2[b].x2;
		  		y1 = points.line.y1[b].y1;
		  		y2 = points.line.y2[b].y2;
			
			}
		  
			if(theVideo.currentTime == 0)
				{x1=-1000; y1=-1000; b=0; blank(noLayer);}
		    
			if(theVideo.currentTime > 0 && theVideo.paused){
				b = videoTime();
			}
			
			if(theVideo.currentTime > 0 && !theVideo.paused){
				b = videoTime();
				blank(noLayer);
			}
			  	  				
			ctx[noLayer].strokeStyle = black;
			ctx[noLayer].strokeRect(x1,y1,x2,y2);
		};
		  
		var c=0;
		var point = function(){
		  
			var x1,y1;
			var noLayer=numLayer(parseInt(c), "point");			
			  
			if(!theVideo.currentTime == 0 && !theVideo.paused)
			{			
				x1 = points.point.x1[c].x1;
			  	y1 = points.point.y1[c].y1;				
			}
			  
			if(theVideo.currentTime == 0)
				{x1=-1000; y1=-1000; c=0; blank(noLayer);}
		    
			if(theVideo.currentTime > 0 && theVideo.paused){
				c = videoTime();
			}
			
			if(theVideo.currentTime > 0 && !theVideo.paused){
				c = videoTime();
				blank(noLayer);
			}
			  		  		  			
			ctx[noLayer].strokeStyle = black;
			ctx[noLayer].strokeRect(x1,y1);
		};
		  
		  var d=0;
		  var circle = function(){
		  
			  var r,x1,y1;
			  var noLayer=numLayer(parseInt(d), "circle");
			   
			  if(!theVideo.currentTime == 0 && !theVideo.paused)
			  {			
				  	r = points.circle.r[d].r;
				  	x1 = points.circle.x1[d].x1;
				  	y1 = points.circle.y1[d].y1;
				  			 
			  }
			  
			if(theVideo.currentTime == 0)
				{x1=-1000; y1=-1000; d=0; blank(noLayer);}
		    
			if(theVideo.currentTime > 0 && theVideo.paused){
				d = videoTime();
			}
			
			if(theVideo.currentTime > 0 && !theVideo.paused){
				d = videoTime();
				blank(noLayer);
			}
			  	
			  ctx[noLayer].strokeStyle = 'black';
			  ctx[noLayer].beginPath();
			  ctx[noLayer].arc(x1,y1,r,0,2*Math.PI, false);
			  ctx[noLayer].stroke();
		  };
		  
		  var e=0;
		  var oval = function(){
		  
			  var x1, y1, height, width;			  
			  var noLayer=numLayer(parseInt(e), "oval");
			  
			  if(!theVideo.currentTime == 0 && !theVideo.paused)
			  {			
				  x1 = points.oval.x1[e].x1;
				  y1 = points.oval.y1[e].y1;
				  height = points.oval.height[e].height;
				  width = points.oval.width[e].width;
			  }
			  
			  if(theVideo.currentTime == 0)
				  {x1=-1000; y1=-1000; e=0; blank(noLayer);}
		    
			  if(theVideo.currentTime > 0 && theVideo.paused){
				   e = videoTime();
			  }
			
			  if(theVideo.currentTime > 0 && !theVideo.paused){
				  e = videoTime();
				  blank(noLayer);
			  }
			  
			  ctx[noLayer].strokeStyle = 'black';
			  ctx[noLayer].scale(width/height,1);
			  ctx[noLayer].beginPath();
			  if(width<height)
			  	ctx[noLayer].arc(x1,y1,width,0,2*Math.PI, false);
			  else
			  	ctx[noLayer].arc(x1,y1,height,0,2*Math.PI, false);
			  ctx[noLayer].stroke();
		  };
		  
		  var t=0;
		  var text = function(i){
				
			var x1,y1,text,length
			var foundLength;
			
			if(t == points.text[i].x1.length)
				t--;
			
			if(t < points.text[i].x1.length)
				noLayer+=numLayer(i,t,"text");
			
			if(!theVideo.currentTime == 0 && !theVideo.paused)
			{			
				  x1 = points.text[i].x1[t].x1;
				  y1 = points.text[i].y1[t].y1;
				  text = points.text[i].text[t].text;
				  length = points.text[i].length[t].length;
			}
			if(length!=0)  
				foundLength = t;
			if(theVideo.currentTime == 0)
				  {x1=-1000; y1=-1000; t=0; blank(noLayer);}
		    
			if(theVideo.currentTime > 0 && theVideo.paused){
				   t = videoTime();
			  }
			
			if(theVideo.currentTime > 0 && !theVideo.paused){
				  t = videoTime();
				  blank(noLayer);
			  }
			if( t < length + foundLength)
				  t = foundLength;
				
			ctx[noLayer].font = '18pt Calibri';
        	ctx[noLayer].fillStyle = 'red';
        	ctx[noLayer].fillText(text, x1, y1);
		 }		
		
		//  Izracun width, height in ratio videa  
		theVideo.addEventListener('loadedmetadata', function() {
			ratio = theVideo.videoWidth / theVideo.videoHeight;				
			w = theVideo.videoWidth;				
			h = parseInt(w/ratio, 10);	
			for(var i=0; i<theCanvas.length; i++)
			{			
				theCanvas[i].width=w; 
				theCanvas[i].height=h; 
			}
			$('#loading').addClass("hide");
			$("#Layer1").css("border","dotted",3,"black");
			$("#progressBar").removeClass("hide").addClass("showPlayed");
			$("#playPause").removeClass("hide").addClass("showPlayed");
			$("#stop").removeClass("hide").addClass("showPlayed");
			$("#textInput").removeClass("hide").addClass("show");
			$("#buttons").removeClass("hide").addClass("show");
			$("#textInput").css("margin-top",h-h-h-60); $("#textInput").css("left", w+200);
			$("#buttons").css("margin-top",h-h-h-60); $("#buttons").css("left", w);
			$("#progressBar").css("margin-top",h+40);
			$("#progressBar").css("width",w);
			$("#playPause").css("margin-top",h+60);
			$("#stop").css("margin-top",h+60);
			document.getElementById("counter").innerHTML = "Frame: ";
			$("#counter").css("margin-left",w-180);  
			
		}, false);	
		
		// Po kliku na play prikaze vse atribute v canvasih
		theVideo.addEventListener('play', function() {
			setInterval(snap, 1000/points.frameRate);
			setInterval(rect, 1000/points.frameRate);
//			if($('#textCanvas').length > 0)
//				setInterval(inputTextDraw, 1000/points.frameRate);
//			setInterval(textShow, 1000/points.frameRate);
		}, false);
		
		theVideo.addEventListener('paused', function() {
			clearInterval();
		}, false);
		theVideo.addEventListener('ended', function() {
			clearInterval();
		}, false);
		
		$("#stop").click(function(){
			theVideo.pause();
			theVideo.currentTime=0;
		});
		
		$("#playPause").click(function(){
			if(theVideo.paused || theVideo.ended)
			{
				document.getElementById("playPause").title = 'play';
				document.getElementById("playPause").innerHTML = "Pause";	
				theVideo.play();
			}
			else
			{
				document.getElementById("playPause").title = 'pause';
				document.getElementById("playPause").innerHTML = "Play";	
				theVideo.pause();	
			}
		});
		
		theVideo.addEventListener('timeupdate', updateProgress, false);
		
		function updateProgress() {
			var value = 0;
			if (theVideo.currentTime > 0) {
				value = Math.floor((100 / theVideo.duration) * theVideo.currentTime);
			}
			document.getElementById("played").style.width = value + "%";
		}
		
		
		var progressBar = document.getElementById("progressBar");
		
		progressBar.addEventListener("mouseup", function(e)
			{ setPlayPosition(e.pageX); }, false);
			
		function setPlayPosition(x) {
			var progressBar = document.getElementById("progressBar");
			var value = (x - findPos(progressBar)).toFixed(2);
			var timeToSet = ((theVideo.duration /
				progressBar.offsetWidth).toFixed(2) * value).toFixed(2);
			theVideo.currentTime = timeToSet;
		}
		
		function findPos(obj) {
			var curleft = 0;
			if (obj.offsetParent) {
				do { curleft += obj.offsetLeft; } while
					(obj = obj.offsetParent);
			}
			return curleft;
		}
		
		$('ul').on('click', 'button', function() {
			
			var buttonElementId = $(this).attr('id');
			var buttonTitle = "#"+$(this).attr('title');	
				
			if($(buttonTitle).hasClass("show"))
			{
				
				$(buttonTitle).removeClass("show").addClass("hide");
				document.getElementById(buttonElementId).innerHTML = "Show";
			}
			else
			{
				$(buttonTitle).removeClass("hide").addClass("show");
				document.getElementById(buttonElementId).innerHTML = "Hide";
			}
		});
		
		/*Tu se zacnejo funkcije, kjer dodajamo layer s textom*/
		
		var textInput; var startFrame; var endFrame; var xCoordinate; var yCoordinate;
		var drawText = {};
		
		$('#submit').click(function() {
        	var textCanvNum=0;
			
			if($('#tinput').val() != null)
				 textInput = $('#tinput').val();
			if($('#sframe').val() != null)
				 startFrame = parseInt($('#sframe').val());
			if($('#eframe').val() != null)
				 endFrame = parseInt($('#eframe').val());
		    if($('#xcoord').val() != null)
				 xCoordinate = parseInt($('#xcoord').val());
			if($('#ycoord').val() != null)
				 yCoordinate = parseInt($('#ycoord').val());	
			
			
			if(startFrame >= 0 && startFrame <= points.rect.x1.length && endFrame >=  0 && endFrame <= points.rect.x1.length
			&& xCoordinate >= 0 && xCoordinate <= theCanvas[0].width && yCoordinate >= 0 && yCoordinate <= theCanvas[0].height 
			&& startFrame < endFrame){
				textCanvNum = numCanvas+parseInt(3);
				$(".canvasPlayer").append("<canvas id='textCanvas' class='show' style='position:absolute; z-index:"+textCanvNum+";' width = "+theCanvas[0].width+" height = "+theCanvas[0].height+"></canvas>");
				canvasName[numCanvas+parseInt(1)] = {"layer" : "textCanvas"};
				theCanvas[numCanvas+parseInt(2)] = document.getElementById("textCanvas");
				ctx[numCanvas+parseInt(2)] = theCanvas[numCanvas+parseInt(2)].getContext("2d");
				drawText = {"textInput":textInput, "startFrame":startFrame, "endFrame":endFrame, "xCoordinate":xCoordinate, "yCoordinate":yCoordinate, "textCanvNum":textCanvNum-1};
			}
			else{
				alert("wrong data");
				$('#textCanvas').remove();
			}
    	});		
		
		var inputTextDraw = function(){
			if(Math.floor((theVideo.currentTime*points.frameRate).toPrecision(3))== drawText.startFrame)
			{
				text(drawText.xCoordinate,drawText.yCoordinate,drawText.textInput, drawText.textCanvNum); 
			}
			else if(Math.floor((theVideo.currentTime*points.frameRate).toPrecision(3)) <= drawText.endFrame)
				{}
			else
				blank(drawText.textCanvNum);	
	  }
		
		theVideo.addEventListener('play', function(){
			
		}, false);
		
	});		