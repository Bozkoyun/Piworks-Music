package com.piworks.music.util;

import com.opencsv.CSVReader;
import com.piworks.music.entity.Music;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CSVReaderToList {

    private final String csvFileLocation = "src\\main\\resources\\exData\\exhibitA-input.csv";
    final static Logger logger = Logger.getLogger(CSVReaderToList.class);

    private String[] line;
    private String cvsSplitBy = " ";
    private CSVReader reader = null;
    List<Music> musicList=new ArrayList<Music>();
    SimpleDateFormat dformat = new SimpleDateFormat("dd/mm/yyyy");
    private Date date;
    private String StringDate = null;



    public CSVReaderToList()  {
    }
    public List<Music> readToCsv() throws ParseException {
        try {

            Music music=new Music();
            Date csvDate=dformat.parse("10/08/2016");
            reader = new CSVReader(new FileReader(csvFileLocation));
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String[] parsingInfo=line[0].split("[\\s ]+");
                System.out.println(line[0].toString());
                //logger.info(line[0].toString());
                music.setPlayId(parsingInfo[0].getBytes());
                music.setSongId(Integer.parseInt(parsingInfo[1]));
                music.setClientId(Integer.parseInt(parsingInfo[2]));
                SimpleDateFormat formatter;
                if(parsingInfo.length>=5) {

                    StringDate=parsingInfo[3]+" "+parsingInfo[4];
                    formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                    date=formatter.parse(StringDate);
                }else {
                    StringDate=parsingInfo[3];
                    formatter = new SimpleDateFormat("dd/mm/yyyy");
                    date=formatter.parse(StringDate);
                }


                music.setPlayTs(date);
                if(csvDate.equals(dformat.format(music.getPlayTs()))) {
                    musicList.add(music);
                }
            }
        } catch (IOException e) {
            logger.error("This is error readToCsvOperation " + e.toString());
            e.printStackTrace();
        }

          return musicList;
    }
}
