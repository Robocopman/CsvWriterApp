import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Robin on 1/31/14.
 */
public class CsvWriterImpl implements CsvWriter {

    //TODO: Refactoring


    @Override
    public synchronized void write(String path, String fileName){
        Random random = new Random();
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        File file;
        int randomAmountOfEntries;
        GregorianCalendar cal = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        int randomNumberForThread;
        List<Integer> randomNumberForThreadList = new ArrayList<>();
        try {
            randomNumberForThread = generateRandom(random, 80);
            randomAmountOfEntries = generateRandom(random, 10000) + 1;
            while(randomNumberForThreadList.contains(randomNumberForThread)){
                randomNumberForThread = random.nextInt(10000);
            }
            randomNumberForThreadList.add(randomNumberForThread);
            file = new File(path + "\\" + fileName + randomNumberForThread + ".csv");
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            writeHeaders(bufferedWriter);
            for (int i = 0; i < randomAmountOfEntries; i++){
                writeLine(random, bufferedWriter, cal, df);
            }
            closeWriters(fileWriter, bufferedWriter);
        } catch(FileNotFoundException fnE){
            exceptionMessage(fnE);
        } catch(IOException ioE){
            exceptionMessage(ioE);
        }

    }

    private int generateRandom(Random random, int number){
        return random.nextInt(number);
    }

    private void writeLine(Random random, BufferedWriter bufferedWriter, GregorianCalendar cal, DateFormat df) throws IOException {
        String randomDateString;
        bufferedWriter.write(Double.toString(random.nextDouble() * 100));
        bufferedWriter.write(",");
        randomDateString = createRandomDate(random, cal, df);
        bufferedWriter.write(randomDateString);
        bufferedWriter.newLine();
    }

    private String createRandomDate(Random random, GregorianCalendar cal, DateFormat df) {
        int randomDayOfTheYear;
        Date randomDate;
        String randomDateString;
        randomDayOfTheYear = random.nextInt(365) + 1;
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.DAY_OF_YEAR, randomDayOfTheYear);
        randomDate = cal.getTime();
        randomDateString = df.format(randomDate);
        return randomDateString;
    }

    private void writeHeaders(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("Random Number");
        bufferedWriter.write(",");
        bufferedWriter.write("Random Date");
        bufferedWriter.newLine();
    }

    private void closeWriters(FileWriter fileWriter, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }

    private void exceptionMessage(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
        e.printStackTrace();
    }
}
