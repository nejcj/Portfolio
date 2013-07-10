FloatTable data1,data2,data3,data4,data5,data6,data7,data8,data9,data10;

float pMin, pMax;

float plotX1,plotX2,plotY1,plotY2,labelX,labelY;

int currentRow = 0;
int currentColumn=0;
int rowCount,columnCount;

int letoMin, letoMax;
int pozMin=0; int pozMax=26;
int tocMin=0; int tocMax=160;

int [] leta;

int tocInterval = 20;
int tocIntervalMinor = 10;

Integrator[][][] vozniki;

PFont plotFont;

PImage puscica_levo;
PImage puscica_desno;

float[] tabLeft, tabRight;  
float tabTop, tabBottom;
float tabPad = 10;

boolean tipka,klik;

void setup()
{
  
    size(1300,800);
  
    data1 = new FloatTable("barichello.txt");
    data2 = new FloatTable("msch.txt");
    data3 = new FloatTable("coulthard.txt");
    data4 = new FloatTable("alonso.txt");
    data5 = new FloatTable("button.txt");
    data6 = new FloatTable("hakinnen.txt");
    data7 = new FloatTable("irvine.txt");
    data8 = new FloatTable("rsch.txt");
    data9 = new FloatTable("trulli.txt");
    data10 = new FloatTable("senna.txt");
  
    rowCount = data1.getRowCount();
    columnCount = data1.getColumnCount();
 
    leta = int(data1.getRowNames());
    letoMin = leta[0];
    letoMax = leta[leta.length - 1];
    
    vozniki = new Integrator[10][3][rowCount];
    
    for (int row = 0; row < rowCount; row++) 
    {
            
      // pozicije
      float initialValue1_1 = pozMax-data1.getFloat(row, 1);
      vozniki[0][0][row] = new Integrator(initialValue1_1);
      vozniki[0][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_2 = pozMax-data2.getFloat(row, 1);
      vozniki[1][0][row] = new Integrator(initialValue1_2);
      vozniki[1][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_3 = pozMax-data3.getFloat(row, 1);
      vozniki[2][0][row] = new Integrator(initialValue1_3);
      vozniki[2][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_4 = pozMax-data4.getFloat(row, 1);
      vozniki[3][0][row] = new Integrator(initialValue1_4);
      vozniki[3][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_5 = pozMax-data5.getFloat(row, 1);
      vozniki[4][0][row] = new Integrator(initialValue1_5);
      vozniki[4][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_6 = pozMax-data6.getFloat(row, 1);
      vozniki[5][0][row] = new Integrator(initialValue1_6);
      vozniki[5][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_7 = pozMax-data7.getFloat(row, 1);
      vozniki[6][0][row] = new Integrator(initialValue1_7);
      vozniki[6][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_8 = pozMax-data8.getFloat(row, 1);
      vozniki[7][0][row] = new Integrator(initialValue1_8);
      vozniki[7][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_9 = pozMax-data9.getFloat(row, 1);
      vozniki[8][0][row] = new Integrator(initialValue1_9);
      vozniki[8][0][row].attraction = 0.1;  // Set lower than the default
      float initialValue1_10 = pozMax-data10.getFloat(row, 1);
      vozniki[9][0][row] = new Integrator(initialValue1_10);
      vozniki[9][0][row].attraction = 0.1;  // Set lower than the default
      
      
      //točke
      float initialValue2_1 = data1.getFloat(row, 0);
      vozniki[0][1][row] = new Integrator(initialValue2_1);
      vozniki[0][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_2 = data2.getFloat(row, 0);
      vozniki[1][1][row] = new Integrator(initialValue2_2);
      vozniki[1][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_3 = data3.getFloat(row, 0);
      vozniki[2][1][row] = new Integrator(initialValue2_3);
      vozniki[2][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_4 = data4.getFloat(row, 0);
      vozniki[3][1][row] = new Integrator(initialValue2_4);
      vozniki[3][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_5 = data5.getFloat(row, 0);
      vozniki[4][1][row] = new Integrator(initialValue2_5);
      vozniki[4][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_6 = data6.getFloat(row, 0);
      vozniki[5][1][row] = new Integrator(initialValue2_6);
      vozniki[5][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_7 = data7.getFloat(row, 0);
      vozniki[6][1][row] = new Integrator(initialValue2_7);
      vozniki[6][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_8 = data8.getFloat(row, 0);
      vozniki[7][1][row] = new Integrator(initialValue2_8);
      vozniki[7][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_9 = data9.getFloat(row, 0);
      vozniki[8][1][row] = new Integrator(initialValue2_9);
      vozniki[8][1][row].attraction = 0.1;  // Set lower than the default
      float initialValue2_10 = data10.getFloat(row, 0);
      vozniki[9][1][row] = new Integrator(initialValue2_10);
      vozniki[9][1][row].attraction = 0.1;  // Set lower than the default
      
      
      //index
      float initialValue3_1 = data1.getFloat(row, 2);
      vozniki[0][2][row] = new Integrator(initialValue3_1);
      vozniki[0][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_2 = data2.getFloat(row, 2);
      vozniki[1][2][row] = new Integrator(initialValue3_2);
      vozniki[1][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_3 = data3.getFloat(row, 2);
      vozniki[2][2][row] = new Integrator(initialValue3_3);
      vozniki[2][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_4 = data4.getFloat(row, 2);
      vozniki[3][2][row] = new Integrator(initialValue3_4);
      vozniki[3][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_5 = data5.getFloat(row, 2);
      vozniki[4][2][row] = new Integrator(initialValue3_5);
      vozniki[4][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_6 = data6.getFloat(row, 2);
      vozniki[5][2][row] = new Integrator(initialValue3_6);
      vozniki[5][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_7 = data7.getFloat(row, 2);
      vozniki[6][2][row] = new Integrator(initialValue3_7);
      vozniki[6][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_8 = data8.getFloat(row, 2);
      vozniki[7][2][row] = new Integrator(initialValue3_8);
      vozniki[7][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_9 = data9.getFloat(row, 2);
      vozniki[8][2][row] = new Integrator(initialValue3_9);
      vozniki[8][2][row].attraction = 0.1;  // Set lower than the default
      float initialValue3_10 = data10.getFloat(row, 2);
      vozniki[9][2][row] = new Integrator(initialValue3_10);
      vozniki[9][2][row].attraction = 0.1;  // Set lower than the default
  
    }
  
  
    plotX1 = 120;
    plotX2 = width - 200;
    plotY1 = 60;
    plotY2 = height - 120;
    labelX = 50;
    labelY = height - 50;
  
  
    plotFont = createFont("AgencyFB-Bold-48", 20);
    textFont(plotFont);
  
    smooth();
}

void draw()
{
  background(224);
  
  fill(255);
  rectMode(CORNERS);
  noStroke();
  smooth();
  rect(plotX1,plotY1,plotX2,plotY2);
  
  drawTitle();
  drawPlaces();
  drawPoints();
  drawXLabels();
  drawDrivers();
  drawSmerniki();
  
  
  for(int i=0;i<9;i++)
  {
    for(int j=0;j<3;j++)
    {
      vozniki[i][j][currentRow].update();
    }
  }
   
  drawDataPoints(currentRow,0,#2F9B25,#EAE41C,"BAR");
  drawDataPoints(currentRow,1,#E02A24,#000000,"MSC");
  drawDataPoints(currentRow,2,#FFFFFF,#0C38AD,"COU");
  drawDataPoints(currentRow,3,#D7DE1D,#DE1B1B,"ALO");
  drawDataPoints(currentRow,4,#202AD6,#F21D1D,"BUT");
  drawDataPoints(currentRow,5,#707071,#000000,"HAK");
  drawDataPoints(currentRow,6,#D0E531,#276A21,"IRV");
  drawDataPoints(currentRow,7,#E0E022,#000000,"RSC");
  drawDataPoints(currentRow,8,#FFFFFF,#27AA26,"TRU");
  drawDataPoints(currentRow,9,#FFFFFF,#E02A24,"SEN");
  
  drawDataHighlight(currentRow,0,#2F9B25);
  drawDataHighlight(currentRow,1,#E02A24);
  drawDataHighlight(currentRow,2,#FFFFFF);
  drawDataHighlight(currentRow,3,#D7DE1D);
  drawDataHighlight(currentRow,4,#202AD6);
  drawDataHighlight(currentRow,5,#707071);
  drawDataHighlight(currentRow,6,#D0E531);
  drawDataHighlight(currentRow,7,#E0E022);
  drawDataHighlight(currentRow,8,#FFFFFF); 
  drawDataHighlight(currentRow,9,#FFFFFF); 
}

void drawSmerniki()
{
  puscica_levo = loadImage("bullet-green-2.png");
  puscica_desno = loadImage("bullet-green-1.png");
  
  image(puscica_levo,plotX1+440,plotY1-45,40,40);
  image(puscica_desno,plotX1+480,plotY1-45,40,40);
}

void drawTitle()
{
  fill(0);
  textSize(22);
  textAlign(CENTER);
  String title ="Sezona "+data1.getRowName(currentRow);
  text(title,plotX1+65,plotY1-15);
}

void drawXLabels()
{
  fill(0);
  textSize(13);
  textLeading(15);
  
  textAlign(CENTER, CENTER);
  // Use \n (enter/linefeed) to break the text into separate lines
  text("Točke v\n svetovnem\n pokalu", labelX, (plotY1+plotY2)/2);
  textAlign(CENTER);
  text("Pozicija v svetovnem pokalu", (plotX1+plotX2)/2, labelY);
} 

void drawDrivers()
{
  textAlign(LEFT);
  text("BAR\nRubens Barichello",plotX2+5,plotY1+13);
  textAlign(LEFT);
  text("MSC\nMichael Schumacher",plotX2+5,plotY1+49);
  textAlign(LEFT);
  text("SEN\nAyrton Senna",plotX2+5,plotY1+85);
  textAlign(LEFT);
  text("COU\nDavid Coulthard",plotX2+5,plotY1+121);
  textAlign(LEFT);
  text("HAK\nMika Hakkinen",plotX2+5,plotY1+157);
  textAlign(LEFT);
  text("BUT\nJenson Button",plotX2+5,plotY1+193);
  textAlign(LEFT);
  text("TRU\nJarno Trulli",plotX2+5,plotY1+229);
  textAlign(LEFT);
  text("RSC\nRalf Schumacher",plotX2+5,plotY1+265);
  textAlign(LEFT);
  text("ALO\nFernando Alonso",plotX2+5,plotY1+301);
  textAlign(LEFT);
  text("IRV\nEddie Irvine",plotX2+5,plotY1+337);
  
  fill(#2F9B25);
  strokeWeight(2);
  stroke(#EAE41C);
  ellipse(plotX2+150,plotY1+13,20,20);
  
  fill(#E02A24);
  strokeWeight(2);
  stroke(#000000);
  ellipse(plotX2+150,plotY1+49,20,20);
  
  fill(#FFFFFF);
  strokeWeight(2);
  stroke(#E02A24);
  ellipse(plotX2+150,plotY1+85,20,20);
  
  fill(#FFFFFF);
  strokeWeight(2);
  stroke(#0C38AD);
  ellipse(plotX2+150,plotY1+121,20,20);
  
  fill(#707071);
  strokeWeight(2);
  stroke(#000000);
  ellipse(plotX2+150,plotY1+157,20,20);
  
  fill(#202AD6);
  strokeWeight(2);
  stroke(#F21D1D);
  ellipse(plotX2+150,plotY1+193,20,20);
  
  fill(#FFFFFF);
  strokeWeight(2);
  stroke(#27AA26);
  ellipse(plotX2+150,plotY1+229,20,20);
  
  fill(#E0E022);
  strokeWeight(2);
  stroke(#000000);
  ellipse(plotX2+150,plotY1+265,20,20);
  
  fill(#D7DE1D);
  strokeWeight(2);
  stroke(#DE1B1B);
  ellipse(plotX2+150,plotY1+301,20,20);
  
  fill(#D0E531);
  strokeWeight(2);
  stroke(#276A21);
  ellipse(plotX2+150,plotY1+337,20,20);
  
}

void drawPlaces()
{
  fill(0);
  textSize(10);
  textAlign(CENTER, TOP);
  
  // Use thin, gray lines to draw the grid
  stroke(224);
  strokeWeight(1);
  
  for (int row = pozMax-1; row > pozMin; row--) 
  {
      float x = map(row, pozMax, pozMin, plotX1, plotX2);
      line(x, plotY1, x, plotY2);
      if(row!=pozMin)
      {
        text(row, x, plotY2 + 25);
      }
  }
}


void drawPoints()
{
  fill(0);
  textSize(10);
  textAlign(RIGHT);
  
  stroke(128);
  strokeWeight(1);
  
  for (int col = tocMin; col < tocMax; col+=tocIntervalMinor) 
  {
    if(col%tocIntervalMinor==0)
    {
      float y = map(col, tocMin, tocMax, plotY2, plotY1);
      if(col%tocInterval==0)
      {
        float textOffset = textAscent()/2;
        
        if(col==tocMin)
        {
          textOffset=0;
        }
        else if(col == tocMax)
        {
          textOffset = textAscent();
        }
        text(floor(col), plotX1 - 10, y + textOffset);
        line(plotX1 - 4, y, plotX1, y);
      }
      else
      {
        line(plotX1 - 2, y, plotX1, y);
      }
    } 
  }
  
}

void drawDataPoints(int row, int voz, int barva1, int barva2, String besVoz)
{
  int colCount = data1.getColumnCount(); 
  for (int col = 0; col < colCount; col++) 
  { 
      float x = map(vozniki[voz][0][row].value,0,pozMax,plotX1,plotX2);
      float y = map(vozniki[voz][1][row].value,0,tocMax,plotY2,plotY1);
      float premer = vozniki[voz][2][row].value*5;
      fill(barva1);
      strokeWeight(premer/10);
      stroke(barva2);
      ellipse(x,y,premer,premer);
     
      if(vozniki[voz][0][row].value==pozMax)
      {
        textSize(0);
        text("");
      }
      
      else
      {
        textSize(10);
        textAlign(CENTER);
        fill(#000000);
        text(besVoz,x,(y+vozniki[voz][2][row].value*4.2));
        textAlign(LEFT);
      }
    } 
}


void drawDataHighlight(int row, int voz, int barva)
{
      float x = map(vozniki[voz][0][row].value,0,pozMax,plotX1,plotX2);
      float y = map(vozniki[voz][1][row].value,0,tocMax,plotY2,plotY1);
      float premer = vozniki[voz][2][row].value;
      if (dist(mouseX, mouseY, x, y) < premer) 
      {
        stroke(barva);
        strokeWeight(2*premer);
        point(x, y);
        fill(0);
        textSize(10);
        textAlign(CENTER);
        text(" Index: "+ premer, x, y-(3.5*premer));
        textAlign(LEFT);
        
        stroke(#D7D6D8);
        strokeWeight(1);
        line(plotX1,y,x,y);
        textAlign(RIGHT);
        text(nf(vozniki[voz][1][row].value, 0, 2),plotX1+38,y);
        textAlign(LEFT);
      }
}

void keyPressed() 
{
  if (key == '-') 
  {
    currentRow--;
    tipka=false;
    if (currentRow < 0) 
    {
      currentRow = rowCount - 1;
    }
    setCurrent(currentRow);
  } 
  else if (key == '+') 
  {
    currentRow++;
    tipka=true;
    if (currentRow == rowCount) 
    {
      currentRow = 0;
    }
     setCurrent(currentRow);
  }
}


void mousePressed()
{
  if(mouseY > 15 && mouseY < 55 && mouseX > 560 && mouseX < 600)
  {
    currentRow--;
    klik=false;
    if (currentRow < 0) 
    {
      currentRow = rowCount - 1;
    }
    setCurrent(currentRow);
  }
  else if(mouseY > 15 && mouseY < 55 && mouseX > 600 && mouseX < 640)
  {
    currentRow++;
    klik=true;
    if (currentRow == rowCount) 
    {
      currentRow = 0;
    }
    setCurrent(currentRow);  
  }
}

void setCurrent(int row) 
{  
    currentRow = row;  
  
    /*
    if(tipka == false || klik==false)
    {
      if(row==0)
      {
        vozniki[0][0][rowCount-1].target(pozMax-data1.getFloat(row,1));
        vozniki[0][1][rowCount-1].target(data1.getFloat(row,0));
        vozniki[0][2][rowCount-1].target(data1.getFloat(row,2));
         
        vozniki[1][0][rowCount-1].target(pozMax-data2.getFloat(row,1));
        vozniki[1][1][rowCount-1].target(data2.getFloat(row,0));
        vozniki[1][2][rowCount-1].target(data2.getFloat(row,2));
       
        vozniki[2][0][rowCount-1].target(pozMax-data3.getFloat(row,1));
        vozniki[2][1][rowCount-1].target(data3.getFloat(row,0));
        vozniki[2][2][rowCount-1].target(data3.getFloat(row,2));
        
        vozniki[3][0][rowCount-1].target(pozMax-data4.getFloat(row,1));
        vozniki[3][1][rowCount-1].target(data4.getFloat(row,0));
        vozniki[3][2][rowCount-1].target(data4.getFloat(row,2));
         
        vozniki[4][0][rowCount-1].target(pozMax-data5.getFloat(row,1));
        vozniki[4][1][rowCount-1].target(data5.getFloat(row,0));
        vozniki[4][2][rowCount-1].target(data5.getFloat(row,2));
      
        vozniki[5][0][rowCount-1].target(pozMax-data6.getFloat(row,1));
        vozniki[5][1][rowCount-1].target(data6.getFloat(row,0));
        vozniki[5][2][rowCount-1].target(data6.getFloat(row,2));
    
        vozniki[6][0][rowCount-1].target(pozMax-data7.getFloat(row,1));
        vozniki[6][1][rowCount-1].target(data7.getFloat(row,0));
        vozniki[6][2][rowCount-1].target(data7.getFloat(row,2));
        
        vozniki[7][0][rowCount-1].target(pozMax-data8.getFloat(row,1));
        vozniki[7][1][rowCount-1].target(data8.getFloat(row,0));
        vozniki[7][2][rowCount-1].target(data8.getFloat(row,2));
        
        vozniki[8][0][rowCount-1].target(pozMax-data9.getFloat(row,1));
        vozniki[8][1][rowCount-1].target(data9.getFloat(row,0));
        vozniki[8][2][rowCount-1].target(data9.getFloat(row,2));
    
        vozniki[9][0][rowCount-1].target(pozMax-data10.getFloat(row,1));
        vozniki[9][1][rowCount-1].target(data10.getFloat(row,0));
        vozniki[9][2][rowCount-1].target(data10.getFloat(row,2));
      }
      else
      {
        vozniki[0][0][row+1].target(pozMax-data1.getFloat(row,1));
        vozniki[0][1][row+1].target(data1.getFloat(row,0));
        vozniki[0][2][row+1].target(data1.getFloat(row,2));
         
        vozniki[1][0][row+1].target(pozMax-data2.getFloat(row,1));
        vozniki[1][1][row+1].target(data2.getFloat(row,0));
        vozniki[1][2][row+1].target(data2.getFloat(row,2));
       
        vozniki[2][0][row+1].target(pozMax-data3.getFloat(row,1));
        vozniki[2][1][row+1].target(data3.getFloat(row,0));
        vozniki[2][2][row+1].target(data3.getFloat(row,2));
        
        vozniki[3][0][row+1].target(pozMax-data4.getFloat(row,1));
        vozniki[3][1][row+1].target(data4.getFloat(row,0));
        vozniki[3][2][row+1].target(data4.getFloat(row,2));
         
        vozniki[4][0][row+1].target(pozMax-data5.getFloat(row,1));
        vozniki[4][1][row+1].target(data5.getFloat(row,0));
        vozniki[4][2][row+1].target(data5.getFloat(row,2));
      
        vozniki[5][0][row+1].target(pozMax-data6.getFloat(row,1));
        vozniki[5][1][row+1].target(data6.getFloat(row,0));
        vozniki[5][2][row+1].target(data6.getFloat(row,2));
    
        vozniki[6][0][row+1].target(pozMax-data7.getFloat(row,1));
        vozniki[6][1][row+1].target(data7.getFloat(row,0));
        vozniki[6][2][row+1].target(data7.getFloat(row,2));
        
        vozniki[7][0][row+1].target(pozMax-data8.getFloat(row,1));
        vozniki[7][1][row+1].target(data8.getFloat(row,0));
        vozniki[7][2][row+1].target(data8.getFloat(row,2));
        
        vozniki[8][0][row+1].target(pozMax-data9.getFloat(row,1));
        vozniki[8][1][row+1].target(data9.getFloat(row,0));
        vozniki[8][2][row+1].target(data9.getFloat(row,2));
    
        vozniki[9][0][row+1].target(pozMax-data10.getFloat(row,1));
        vozniki[9][1][row+1].target(data10.getFloat(row,0));
        vozniki[9][2][row+1].target(data10.getFloat(row,2));
      }
    }
    
    if(tipka==true || klik==true)
    {
      if(row==rowCount-1)
      {
        vozniki[0][0][0].target(pozMax-data1.getFloat(row,1));
        vozniki[0][1][0].target(data1.getFloat(row,0));
        vozniki[0][2][0].target(data1.getFloat(row,2));
         
        vozniki[1][0][0].target(pozMax-data2.getFloat(row,1));
        vozniki[1][1][0].target(data2.getFloat(row,0));
        vozniki[1][2][0].target(data2.getFloat(row,2));
       
        vozniki[2][0][0].target(pozMax-data3.getFloat(row,1));
        vozniki[2][1][0].target(data3.getFloat(row,0));
        vozniki[2][2][0].target(data3.getFloat(row,2));
        
        vozniki[3][0][0].target(pozMax-data4.getFloat(row,1));
        vozniki[3][1][0].target(data4.getFloat(row,0));
        vozniki[3][2][0].target(data4.getFloat(row,2));
         
        vozniki[4][0][0].target(pozMax-data5.getFloat(row,1));
        vozniki[4][1][0].target(data5.getFloat(row,0));
        vozniki[4][2][0].target(data5.getFloat(row,2));
      
        vozniki[5][0][0].target(pozMax-data6.getFloat(row,1));
        vozniki[5][1][0].target(data6.getFloat(row,0));
        vozniki[5][2][0].target(data6.getFloat(row,2));
    
        vozniki[6][0][0].target(pozMax-data7.getFloat(row,1));
        vozniki[6][1][0].target(data7.getFloat(row,0));
        vozniki[6][2][0].target(data7.getFloat(row,2));
          
        vozniki[7][0][0].target(pozMax-data8.getFloat(row,1));
        vozniki[7][1][0].target(data8.getFloat(row,0));
        vozniki[7][2][0].target(data8.getFloat(row,2));
          
        vozniki[8][0][0].target(pozMax-data9.getFloat(row,1));
        vozniki[8][1][0].target(data9.getFloat(row,0));
        vozniki[8][2][0].target(data9.getFloat(row,2));
      
        vozniki[9][0][0].target(pozMax-data10.getFloat(row,1));
        vozniki[9][1][0].target(data10.getFloat(row,0));
        vozniki[9][2][0].target(data10.getFloat(row,2));
    }
    else
    {
        vozniki[0][0][row-1].target(pozMax-data1.getFloat(row,1));
        vozniki[0][1][row-1].target(data1.getFloat(row,0));
        vozniki[0][2][row-1].target(data1.getFloat(row,2));
         
        vozniki[1][0][row-1].target(pozMax-data2.getFloat(row,1));
        vozniki[1][1][row-1].target(data2.getFloat(row,0));
        vozniki[1][2][row-1].target(data2.getFloat(row,2));
       
        vozniki[2][0][row-1].target(pozMax-data3.getFloat(row,1));
        vozniki[2][1][row-1].target(data3.getFloat(row,0));
        vozniki[2][2][row-1].target(data3.getFloat(row,2));
        
        vozniki[3][0][row-1].target(pozMax-data4.getFloat(row,1));
        vozniki[3][1][row-1].target(data4.getFloat(row,0));
        vozniki[3][2][row-1].target(data4.getFloat(row,2));
         
        vozniki[4][0][row-1].target(pozMax-data5.getFloat(row,1));
        vozniki[4][1][row-1].target(data5.getFloat(row,0));
        vozniki[4][2][row-1].target(data5.getFloat(row,2));
      
        vozniki[5][0][row-1].target(pozMax-data6.getFloat(row,1));
        vozniki[5][1][row-1].target(data6.getFloat(row,0));
        vozniki[5][2][row-1].target(data6.getFloat(row,2));
    
        vozniki[6][0][row-1].target(pozMax-data7.getFloat(row-1,1));
        vozniki[6][1][row-1].target(data7.getFloat(row,0));
        vozniki[6][2][row-1].target(data7.getFloat(row,2));
        
        vozniki[7][0][row-1].target(pozMax-data8.getFloat(row,1));
        vozniki[7][1][row-1].target(data8.getFloat(row,0));
        vozniki[7][2][row-1].target(data8.getFloat(row,2));
        
        vozniki[8][0][row-1].target(pozMax-data9.getFloat(row,1));
        vozniki[8][1][row-1].target(data9.getFloat(row,0));
        vozniki[8][2][row-1].target(data9.getFloat(row,2));
    
        vozniki[9][0][row-1].target(pozMax-data10.getFloat(row,1));
        vozniki[9][1][row-1].target(data10.getFloat(row,0));
        vozniki[9][2][row-1].target(data10.getFloat(row,2));
     }
    }
    */
    
    vozniki[0][0][row].target(pozMax-data1.getFloat(row,1));
        vozniki[0][1][row].target(data1.getFloat(row,0));
        vozniki[0][2][row].target(data1.getFloat(row,2));
         
        vozniki[1][0][row].target(pozMax-data2.getFloat(row,1));
        vozniki[1][1][row].target(data2.getFloat(row,0));
        vozniki[1][2][row].target(data2.getFloat(row,2));
       
        vozniki[2][0][row].target(pozMax-data3.getFloat(row,1));
        vozniki[2][1][row].target(data3.getFloat(row,0));
        vozniki[2][2][row].target(data3.getFloat(row,2));
        
        vozniki[3][0][row].target(pozMax-data4.getFloat(row,1));
        vozniki[3][1][row].target(data4.getFloat(row,0));
        vozniki[3][2][row].target(data4.getFloat(row,2));
         
        vozniki[4][0][row].target(pozMax-data5.getFloat(row,1));
        vozniki[4][1][row].target(data5.getFloat(row,0));
        vozniki[4][2][row].target(data5.getFloat(row,2));
      
        vozniki[5][0][row].target(pozMax-data6.getFloat(row,1));
        vozniki[5][1][row].target(data6.getFloat(row,0));
        vozniki[5][2][row].target(data6.getFloat(row,2));
    
        vozniki[6][0][row].target(pozMax-data7.getFloat(row,1));
        vozniki[6][1][row].target(data7.getFloat(row,0));
        vozniki[6][2][row].target(data7.getFloat(row,2));
        
        vozniki[7][0][row].target(pozMax-data8.getFloat(row,1));
        vozniki[7][1][row].target(data8.getFloat(row,0));
        vozniki[7][2][row].target(data8.getFloat(row,2));
        
        vozniki[8][0][row].target(pozMax-data9.getFloat(row,1));
        vozniki[8][1][row].target(data9.getFloat(row,0));
        vozniki[8][2][row].target(data9.getFloat(row,2));
    
        vozniki[9][0][row].target(pozMax-data10.getFloat(row,1));
        vozniki[9][1][row].target(data10.getFloat(row,0));
        vozniki[9][2][row].target(data10.getFloat(row,2));
       
}






