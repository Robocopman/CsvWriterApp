/**
 * Created by Robin on 1/31/14.
 */
public class CsvRunnable implements Runnable{
        CsvWriter csvWriter = new CsvWriterImpl();

        @Override
        public void run(){
            csvWriter.write("C:\\TI3\\CSVFiles", "randomCsvFile");
        }
}
