import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Vizualizacija_F1 extends PApplet {

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

public void setup()
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
 
    leta = PApplet.parseInt(data1.getRowNames());
    letoMin = leta[0];
    letoMax = leta[leta.length - 1];
    
    vozniki = new Integrator[10][3][rowCount];
    
    for (int row = 0; row < rowCount; row++) 
    {
            
      // pozicije
      float initialValue1_1 = pozMax-data1.getFloat(row, 1);
      vozniki[0][0][row] = new Integrator(initialValue1_1);
      vozniki[0][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_2 = pozMax-data2.getFloat(row, 1);
      vozniki[1][0][row] = new Integrator(initialValue1_2);
      vozniki[1][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_3 = pozMax-data3.getFloat(row, 1);
      vozniki[2][0][row] = new Integrator(initialValue1_3);
      vozniki[2][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_4 = pozMax-data4.getFloat(row, 1);
      vozniki[3][0][row] = new Integrator(initialValue1_4);
      vozniki[3][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_5 = pozMax-data5.getFloat(row, 1);
      vozniki[4][0][row] = new Integrator(initialValue1_5);
      vozniki[4][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_6 = pozMax-data6.getFloat(row, 1);
      vozniki[5][0][row] = new Integrator(initialValue1_6);
      vozniki[5][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_7 = pozMax-data7.getFloat(row, 1);
      vozniki[6][0][row] = new Integrator(initialValue1_7);
      vozniki[6][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_8 = pozMax-data8.getFloat(row, 1);
      vozniki[7][0][row] = new Integrator(initialValue1_8);
      vozniki[7][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_9 = pozMax-data9.getFloat(row, 1);
      vozniki[8][0][row] = new Integrator(initialValue1_9);
      vozniki[8][0][row].attraction = 0.1f;  // Set lower than the default
      float initialValue1_10 = pozMax-data10.getFloat(row, 1);
      vozniki[9][0][row] = new Integrator(initialValue1_10);
      vozniki[9][0][row].attraction = 0.1f;  // Set lower than the default
      
      
      //to\u010dke
      float initialValue2_1 = data1.getFloat(row, 0);
      vozniki[0][1][row] = new Integrator(initialValue2_1);
      vozniki[0][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_2 = data2.getFloat(row, 0);
      vozniki[1][1][row] = new Integrator(initialValue2_2);
      vozniki[1][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_3 = data3.getFloat(row, 0);
      vozniki[2][1][row] = new Integrator(initialValue2_3);
      vozniki[2][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_4 = data4.getFloat(row, 0);
      vozniki[3][1][row] = new Integrator(initialValue2_4);
      vozniki[3][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_5 = data5.getFloat(row, 0);
      vozniki[4][1][row] = new Integrator(initialValue2_5);
      vozniki[4][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_6 = data6.getFloat(row, 0);
      vozniki[5][1][row] = new Integrator(initialValue2_6);
      vozniki[5][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_7 = data7.getFloat(row, 0);
      vozniki[6][1][row] = new Integrator(initialValue2_7);
      vozniki[6][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_8 = data8.getFloat(row, 0);
      vozniki[7][1][row] = new Integrator(initialValue2_8);
      vozniki[7][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_9 = data9.getFloat(row, 0);
      vozniki[8][1][row] = new Integrator(initialValue2_9);
      vozniki[8][1][row].attraction = 0.1f;  // Set lower than the default
      float initialValue2_10 = data10.getFloat(row, 0);
      vozniki[9][1][row] = new Integrator(initialValue2_10);
      vozniki[9][1][row].attraction = 0.1f;  // Set lower than the default
      
      
      //index
      float initialValue3_1 = data1.getFloat(row, 2);
      vozniki[0][2][row] = new Integrator(initialValue3_1);
      vozniki[0][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_2 = data2.getFloat(row, 2);
      vozniki[1][2][row] = new Integrator(initialValue3_2);
      vozniki[1][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_3 = data3.getFloat(row, 2);
      vozniki[2][2][row] = new Integrator(initialValue3_3);
      vozniki[2][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_4 = data4.getFloat(row, 2);
      vozniki[3][2][row] = new Integrator(initialValue3_4);
      vozniki[3][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_5 = data5.getFloat(row, 2);
      vozniki[4][2][row] = new Integrator(initialValue3_5);
      vozniki[4][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_6 = data6.getFloat(row, 2);
      vozniki[5][2][row] = new Integrator(initialValue3_6);
      vozniki[5][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_7 = data7.getFloat(row, 2);
      vozniki[6][2][row] = new Integrator(initialValue3_7);
      vozniki[6][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_8 = data8.getFloat(row, 2);
      vozniki[7][2][row] = new Integrator(initialValue3_8);
      vozniki[7][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_9 = data9.getFloat(row, 2);
      vozniki[8][2][row] = new Integrator(initialValue3_9);
      vozniki[8][2][row].attraction = 0.1f;  // Set lower than the default
      float initialValue3_10 = data10.getFloat(row, 2);
      vozniki[9][2][row] = new Integrator(initialValue3_10);
      vozniki[9][2][row].attraction = 0.1f;  // Set lower than the default
  
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

public void draw()
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
   
  drawDataPoints(currentRow,0,0xff2F9B25,0xffEAE41C,"BAR");
  drawDataPoints(currentRow,1,0xffE02A24,0xff000000,"MSC");
  drawDataPoints(currentRow,2,0xffFFFFFF,0xff0C38AD,"COU");
  drawDataPoints(currentRow,3,0xffD7DE1D,0xffDE1B1B,"ALO");
  drawDataPoints(currentRow,4,0xff202AD6,0xffF21D1D,"BUT");
  drawDataPoints(currentRow,5,0xff707071,0xff000000,"HAK");
  drawDataPoints(currentRow,6,0xffD0E531,0xff276A21,"IRV");
  drawDataPoints(currentRow,7,0xffE0E022,0xff000000,"RSC");
  drawDataPoints(currentRow,8,0xffFFFFFF,0xff27AA26,"TRU");
  drawDataPoints(currentRow,9,0xffFFFFFF,0xffE02A24,"SEN");
  
  drawDataHighlight(currentRow,0,0xff2F9B25);
  drawDataHighlight(currentRow,1,0xffE02A24);
  drawDataHighlight(currentRow,2,0xffFFFFFF);
  drawDataHighlight(currentRow,3,0xffD7DE1D);
  drawDataHighlight(currentRow,4,0xff202AD6);
  drawDataHighlight(currentRow,5,0xff707071);
  drawDataHighlight(currentRow,6,0xffD0E531);
  drawDataHighlight(currentRow,7,0xffE0E022);
  drawDataHighlight(currentRow,8,0xffFFFFFF); 
  drawDataHighlight(currentRow,9,0xffFFFFFF); 
}

public void drawSmerniki()
{
  puscica_levo = loadImage("bullet-green-2.png");
  puscica_desno = loadImage("bullet-green-1.png");
  
  image(puscica_levo,plotX1+440,plotY1-45,40,40);
  image(puscica_desno,plotX1+480,plotY1-45,40,40);
}

public void drawTitle()
{
  fill(0);
  textSize(22);
  textAlign(CENTER);
  String title ="Formula 1: Sezona "+data1.getRowName(currentRow);
  text(title,plotX1+8,plotY1-15);
}

public void drawXLabels()
{
  fill(0);
  textSize(13);
  textLeading(15);
  
  textAlign(CENTER, CENTER);
  // Use \n (enter/linefeed) to break the text into separate lines
  text("To\u010dke v\n svetovnem\n pokalu", labelX, (plotY1+plotY2)/2);
  textAlign(CENTER);
  text("Pozicija v svetovnem pokalu", (plotX1+plotX2)/2, labelY);
} 

public void drawDrivers()
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
  
  fill(0xff2F9B25);
  strokeWeight(2);
  stroke(0xffEAE41C);
  ellipse(plotX2+150,plotY1+13,20,20);
  
  fill(0xffE02A24);
  strokeWeight(2);
  stroke(0xff000000);
  ellipse(plotX2+150,plotY1+49,20,20);
  
  fill(0xffFFFFFF);
  strokeWeight(2);
  stroke(0xffE02A24);
  ellipse(plotX2+150,plotY1+85,20,20);
  
  fill(0xffFFFFFF);
  strokeWeight(2);
  stroke(0xff0C38AD);
  ellipse(plotX2+150,plotY1+121,20,20);
  
  fill(0xff707071);
  strokeWeight(2);
  stroke(0xff000000);
  ellipse(plotX2+150,plotY1+157,20,20);
  
  fill(0xff202AD6);
  strokeWeight(2);
  stroke(0xffF21D1D);
  ellipse(plotX2+150,plotY1+193,20,20);
  
  fill(0xffFFFFFF);
  strokeWeight(2);
  stroke(0xff27AA26);
  ellipse(plotX2+150,plotY1+229,20,20);
  
  fill(0xffE0E022);
  strokeWeight(2);
  stroke(0xff000000);
  ellipse(plotX2+150,plotY1+265,20,20);
  
  fill(0xffD7DE1D);
  strokeWeight(2);
  stroke(0xffDE1B1B);
  ellipse(plotX2+150,plotY1+301,20,20);
  
  fill(0xffD0E531);
  strokeWeight(2);
  stroke(0xff276A21);
  ellipse(plotX2+150,plotY1+337,20,20);
  
}

public void drawPlaces()
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


public void drawPoints()
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

public void drawDataPoints(int row, int voz, int barva1, int barva2, String besVoz)
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
        fill(0xff000000);
        text(besVoz,x,(y+vozniki[voz][2][row].value*4.2f));
        textAlign(LEFT);
      }
    } 
}


public void drawDataHighlight(int row, int voz, int barva)
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
        text(" Index: "+ premer, x, y-(3.5f*premer));
        textAlign(LEFT);
        
        stroke(0xffD7D6D8);
        strokeWeight(1);
        line(plotX1,y,x,y);
        textAlign(RIGHT);
        text(nf(vozniki[voz][1][row].value, 0, 2),plotX1+38,y);
        textAlign(LEFT);
      }
}

public void keyPressed() 
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


public void mousePressed()
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

public void setCurrent(int row) 
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






// first line of the file should be the column headers
// first column should be the row titles
// all other values are expected to be floats
// getFloat(0, 0) returns the first data value in the upper lefthand corner
// files should be saved as "text, tab-delimited"
// empty rows are ignored
// extra whitespace is ignored


class FloatTable {
  int rowCount;
  int columnCount;
  float[][] data;
  String[] rowNames;
  String[] columnNames;
  
  
  FloatTable(String filename) {
    String[] rows = loadStrings(filename);
    
    String[] columns = split(rows[0], TAB);
    columnNames = subset(columns, 1); // upper-left corner ignored
    scrubQuotes(columnNames);
    columnCount = columnNames.length;

    rowNames = new String[rows.length-1];
    data = new float[rows.length-1][];

    // start reading at row 1, because the first row was only the column headers
    for (int i = 1; i < rows.length; i++) {
      if (trim(rows[i]).length() == 0) {
        continue; // skip empty rows
      }
      if (rows[i].startsWith("#")) {
        continue;  // skip comment lines
      }

      // split the row on the tabs
      String[] pieces = split(rows[i], TAB);
      scrubQuotes(pieces);
      
      // copy row title
      rowNames[rowCount] = pieces[0];
      // copy data into the table starting at pieces[1]
      data[rowCount] = parseFloat(subset(pieces, 1));

      // increment the number of valid rows found so far
      rowCount++;      
    }
    // resize the 'data' array as necessary
    data = (float[][]) subset(data, 0, rowCount);
  }
  
  
  public void scrubQuotes(String[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].length() > 2) {
        // remove quotes at start and end, if present
        if (array[i].startsWith("\"") && array[i].endsWith("\"")) {
          array[i] = array[i].substring(1, array[i].length() - 1);
        }
      }
      // make double quotes into single quotes
      array[i] = array[i].replaceAll("\"\"", "\"");
    }
  }
  
  
  public int getRowCount() {
    return rowCount;
  }
  
  
  public String getRowName(int rowIndex) {
    return rowNames[rowIndex];
  }
  
  
  public String[] getRowNames() {
    return rowNames;
  }

  
  // Find a row by its name, returns -1 if no row found. 
  // This will return the index of the first row with this name.
  // A more efficient version of this function would put row names
  // into a Hashtable (or HashMap) that would map to an integer for the row.
  public int getRowIndex(String name) {
    for (int i = 0; i < rowCount; i++) {
      if (rowNames[i].equals(name)) {
        return i;
      }
    }
    //println("No row named '" + name + "' was found");
    return -1;
  }
  
  
  // technically, this only returns the number of columns 
  // in the very first row (which will be most accurate)
  public int getColumnCount() {
    return columnCount;
  }
  
  
  public String getColumnName(int colIndex) {
    return columnNames[colIndex];
  }
  
  
  public String[] getColumnNames() {
    return columnNames;
  }


  public float getFloat(int rowIndex, int col) {
    // Remove the 'training wheels' section for greater efficiency
    // It's included here to provide more useful error messages
    
    // begin training wheels
    if ((rowIndex < 0) || (rowIndex >= data.length)) {
      throw new RuntimeException("There is no row " + rowIndex);
    }
    if ((col < 0) || (col >= data[rowIndex].length)) {
      throw new RuntimeException("Row " + rowIndex + " does not have a column " + col);
    }
    // end training wheels
    
    return data[rowIndex][col];
  }
  
  
  public boolean isValid(int row, int col) {
    if (row < 0) return false;
    if (row >= rowCount) return false;
    //if (col >= columnCount) return false;
    if (col >= data[row].length) return false;
    if (col < 0) return false;
    return !Float.isNaN(data[row][col]);
  }


  public float getColumnMin(int col) {
    float m = Float.MAX_VALUE;
    for (int row = 0; row < rowCount; row++) {
      if (isValid(row, col)) {
        if (data[row][col] < m) {
          m = data[row][col];
        }
      }
    }
    return m;
  }


  public float getColumnMax(int col) {
    float m = -Float.MAX_VALUE;
    for (int row = 0; row < rowCount; row++) {
      if (isValid(row, col)) {
        if (data[row][col] > m) {
          m = data[row][col];
        }
      }
    }
    return m;
  }

  
  public float getRowMin(int row) {
    float m = Float.MAX_VALUE;
    for (int col = 0; col < columnCount; col++) {
      if (isValid(row, col)) {
        if (data[row][col] < m) {
          m = data[row][col];
        }
      }
    }
    return m;
  } 


  public float getRowMax(int row) {
    float m = -Float.MAX_VALUE;
    for (int col = 0; col < columnCount; col++) {
      if (isValid(row, col)) {
        if (data[row][col] > m) {
          m = data[row][col];
        }
      }
    }
    return m;
  }


  public float getTableMin() {
    float m = Float.MAX_VALUE;
    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < columnCount; col++) {
        if (isValid(row, col)) {
          if (data[row][col] < m) {
            m = data[row][col];
          }
        }
      }
    }
    return m;
  }


  public float getTableMax() {
    float m = -Float.MAX_VALUE;
    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < columnCount; col++) {
        if (isValid(row, col)) {
          if (data[row][col] > m) {
            m = data[row][col];
          }
        }
      }
    }
    return m;
  }
}
class Integrator 
{

  final float DAMPING = 0.5f;
  final float ATTRACTION = 0.2f;

  float value;
  float vel;
  float accel;
  float force;
  float mass = 1;

  float damping = DAMPING;
  float attraction = ATTRACTION;
  boolean targeting;
  float target;


  Integrator() { }


  Integrator(float value) {
    this.value = value;
  }


  Integrator(float value, float damping, float attraction) {
    this.value = value;
    this.damping = damping;
    this.attraction = attraction;
  }


  public void set(float v) {
    value = v;
  }


  public void update() {
    if (targeting) {
      force += attraction * (target - value);      
    }

    accel = force / mass;
    vel = (vel + accel) * damping;
    value += vel;

    force = 0;
  }


  public void target(float t) {
    targeting = true;
    target = t;
  }


  public void noTarget() {
    targeting = false;
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "Vizualizacija_F1" });
  }
}
