import com.piworks.music.entity.Music;
import com.piworks.music.util.CSVReaderToList;

import java.text.ParseException;
import java.util.*;
import org.apache.log4j.Logger;

public class ManagmentApplication {
   private static List<Music> musicList=new ArrayList<Music>();
    final static Logger logger = Logger.getLogger(ManagmentApplication.class);
    public static void main(String[] args) throws ParseException {

        CSVReaderToList objCsvreader=new CSVReaderToList();
        //Answer1
        musicList=objCsvreader.readToCsv();
        clinetIdOperations(musicList);

    }

    public static void clinetIdOperations(List<Music> musicLists){

        Map<Integer,HashSet<Integer>> clientDisc=new HashMap<Integer,HashSet<Integer>>();
        for(Music music:musicLists) {
            HashSet<Integer> hsongID=clientDisc.get(music.getClientId());
            if(hsongID== null) {
                clientDisc.put(music.getClientId(), new HashSet<Integer>());
                hsongID=clientDisc.get(music.getClientId());
            }
            hsongID.add(music.getSongId());
        }

        Map<Integer,HashSet<Integer>> operClient=new HashMap<Integer,HashSet<Integer>>();
        for(Map.Entry<Integer, HashSet<Integer>> entry : clientDisc.entrySet()) {
            Integer clientId=entry.getKey();
            Integer songCount=entry.getValue().size();

            HashSet<Integer> hs=operClient.get(songCount);
            if(hs== null) {
                operClient.put(songCount, new HashSet<Integer>());
                hs=operClient.get(songCount);
            }
            hs.add(clientId);
        }

        for(Map.Entry<Integer, HashSet<Integer>> entry : operClient.entrySet()) {
            Integer clientCount=entry.getKey();
            Integer songCount=entry.getValue().size();
            logger.info("This is info for clientCount: " + clientCount+" and  songs count:"+songCount);

        }
    }

}
