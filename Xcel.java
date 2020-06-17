import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xcel {

	//-------------------for reading data from Excel file starts--------------------------------------------	
	public static double[] readXLSXFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream("C:\\Users\\manmo\\Desktop\\aapl.xlsx");
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFWorkbook test = new XSSFWorkbook(); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;
                
                double array[]= new double[12];
                int i=0;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
		
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					//System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
					//System.out.print(cell.getNumericCellValue()+" ");
                                        array[i]=cell.getNumericCellValue();
                                        System.out.print(array[i]+" month "+(i+1));
                                        i++;
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}
	return array;
        }
	//-------------------for reading data from Excel file ends--------------------------------------------	
	
	public static void main(String[] args) throws IOException {
		
              
		double stocks[]= new double[12];
                stocks=readXLSXFile();
                System.out.println();
                
                int i=0;
                 
                //--------------- Investor 1 ---------------
                System.out.println();
                System.out.println(" These are the credentials for GENERAL case with normal average :Investor 1  ");
                System.out.println("----------------------------------------------------------------");
                double average=0, avg=0, sd=0, vol=0, sd1=0;
                
                for(int j=0; j<12;j++)
                avg += stocks[j];
                
                average= avg/12;
                
                System.out.println("Per anum general average is : "+average);

                for(i=0; i<12;i++)
                    sd += ( (stocks[i]-average)*(stocks[i]-average) );
                
                sd1=sd/11;
                System.out.println("SD is : "+sd1);
                  
                vol=Math.sqrt(sd1);
                System.out.println("volatility is : "+vol);
                System.out.println();
              
                //------------------ Investor 2-------------------------
                
                System.out.println();
                System.out.println(" These are the credentials for Investor 2 with 60-40 average  ");
                System.out.println("----------------------------------------------------------------");
                double sum1WA1=0, sum2WA1=0, wa1a=0, wa1b=0, wa1=0;
                for(int k=0; k<6; k++)
                {
                    sum1WA1 += stocks[k];
                }
                wa1a= (sum1WA1/6)*60;       // gives 60% waitage to recent 6 months
                  
                for(int k=6; k<12; k++)
                {
                    sum2WA1 += stocks[k];
                }
                wa1b= (sum2WA1/6)*40;       // gives 40% waitage to remaining 6 months
               
                wa1= (wa1a+wa1b)/100;
                System.out.println("wieghted average by investor 2 is "+wa1);
	
                sd=0;
                sd1=0;
                
                for(i=0; i<12;i++)
                    sd += ( (stocks[i]-wa1)*(stocks[i]-wa1) );
                
                sd1=sd/11;
                System.out.println("SD is : "+sd1);
                
                
                vol=Math.sqrt(sd1);
                System.out.println("volatility is : "+vol);
                
                System.out.println();
                
                //------------------ Investor 3-------------------------
                 
                System.out.println();
                System.out.println(" These are the credentials for Investor 3 with 80-20 average  ");
                System.out.println("----------------------------------------------------------------");
                 
                double sum1WA2=0, sum2WA2=0, wa2a=0, wa2b=0, wa2=0;
                for(int k=0; k<4; k++)
                {
                    sum1WA2 += stocks[k];
                }
                wa2a= (sum1WA2/4)*80;       // gives 80% waitage to recent 4 months
                
                
                for(int k=4; k<12; k++)
                {
                    sum2WA2 += stocks[k];       
                }
                wa2b= (sum2WA2/8)*20;       // gives 20% waitage to remaining months
                
	
                wa2= (wa2a+wa2b)/100;
                System.out.println("wieghted average by Investor 3 is "+wa2);
	
                sd=0;
                sd1=0;
                
                for(i=0; i<12;i++)
                    sd += ( (stocks[i]-wa2)*(stocks[i]-wa2) );
                
                sd1=sd/11;
                System.out.println("SD is : "+sd1);
                
                
                vol=Math.sqrt(sd1);
                System.out.println("volatility is : "+vol);
              
        }

}