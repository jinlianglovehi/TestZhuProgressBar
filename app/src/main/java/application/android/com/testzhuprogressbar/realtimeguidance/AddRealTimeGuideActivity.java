package application.android.com.testzhuprogressbar.realtimeguidance;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import application.android.com.testzhuprogressbar.R;
import application.android.com.testzhuprogressbar.testormlite.FitnessLevelModelDao;

/**
 * Created by jinliang on 17-8-1.
 */

public class AddRealTimeGuideActivity extends Activity {

    private static final String TAG = AddRealTimeGuideActivity.class.getSimpleName();
    private static String TITLE_KEEP_THIS_SPEED="real_time_keep_speed" ;
    private static String TITLE_RUN_FASTER="real_time_run_faster" ;
    private static String TITLE_EVERY_GOOD = "real_time_every_good";
    private static String TITLE_RUN_SLOWER = "real_time_run_slower" ;
    private static String TITLE_TARGER_FINISH ="real_time_target_finish" ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        addData();
        getRealTimeGuideData();


        // 16 Jingzhi  1234   18 , 58
        testDecode();
        byte[] byteString = new byte[4] ;
        byte[] beforeBytes = intToBytes2(3) ;
        byte[] afterBytes  =intToBytes2(5);

        byteString[0] = beforeBytes[0] ;
        byteString[1] = beforeBytes[1] ;
        byteString[2] = afterBytes[0] ;
        byteString[3] = afterBytes[1];

        separtIntValue(3407890);
    }

    private void addData(){
        Object[][] result  ={
                {1,TITLE_KEEP_THIS_SPEED},
                {2,TITLE_KEEP_THIS_SPEED},

                {3,TITLE_RUN_FASTER},

                {6,TITLE_KEEP_THIS_SPEED},

                {7,TITLE_RUN_FASTER},

                {10,TITLE_KEEP_THIS_SPEED},

                {11,TITLE_RUN_FASTER},

                {14,TITLE_KEEP_THIS_SPEED},


                {22,TITLE_KEEP_THIS_SPEED},


                {25,TITLE_RUN_FASTER},
                {26,TITLE_RUN_FASTER},


                {27,TITLE_TARGER_FINISH},
                {28,TITLE_EVERY_GOOD},

                {30,TITLE_EVERY_GOOD},
                {31,TITLE_EVERY_GOOD},
                {32,TITLE_EVERY_GOOD},

                {33,TITLE_RUN_SLOWER},
                {34,TITLE_RUN_SLOWER},
                {35,TITLE_RUN_SLOWER}} ;
        

        RealTimeGuidanceModelDao dao  = new RealTimeGuidanceModelDao(getApplicationContext()) ;

        Object[] curnetArray;
        RealTimeGuidanceModel model ;
        int phraseNumber = 0;
        for (int i = 0; i < result.length; i++) {
            model = new RealTimeGuidanceModel();
            curnetArray= result[i];
            phraseNumber = Integer.parseInt(curnetArray[0].toString());
            model.setPhraseNumber(phraseNumber);
            model.setGuideDesc("real_time_guide_desc_"+phraseNumber);
            model.setGuideTitle(curnetArray[1].toString());
            dao.add(model);
        }


    }

  private void getRealTimeGuideData(){

      RealTimeGuideDBManager dao =new RealTimeGuideDBManager(getApplicationContext());
      SQLiteDatabase db  = dao.initDBManager(getPackageName());
      RealTimeGuidanceModel model = dao.selectItemByPhraseNumber(db,1) ;

      Log.i(TAG," model:"+ model.toString());

  }
  private void separtIntValue(int value){

      byte[] number = intToBytes4(value);
      printBytesData(number);

      byte[] beforeNumber = new   byte[2];
      beforeNumber[0] = number[0] ;
      beforeNumber[1]  =number[1] ;

      byte[] afterNumber  =new byte[2] ;
      afterNumber[0] = number[2] ;
      afterNumber[1] = number[3] ;

      int beforeValue = byte2Toint(beforeNumber);

      int afterValue = byte2Toint(afterNumber);

      Log.i(TAG, " value:"+value+", beforeValue:"+ beforeValue +",afterValue:"+ afterValue);
  }

  private String printBytesData(byte[] bytes){
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      for (int i = 0; i < bytes.length ; i++) {
          sb.append(  bytes[i] +",") ;
      }
      sb.append("]") ;
      Log.i(TAG," printBytesData:"+ sb.toString());
      return sb.toString();

  }

  public static  byte[] intToBytes2(int value){

      byte[] src = new byte[2];
      src[0] = (byte) ((value>>8)&0xFF);
      src[1] = (byte) (value & 0xFF);
      return src;
  }


  public static int byte2Toint(byte[] ary){
      int value;
      int offset = 0 ;
//      value = (int) ((ary[offset]&0xFF)
//              | ((ary[offset+1]<<8) & 0xFF00)) ;

      value = (int) (
              ((ary[0]<<8) & 0xFF00)
                      |(ary[1]&0xFF)

      ) ;

      return value ;
  }
    public static byte[] intToBytes4(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;

    }

//    public static int bytesToInt(byte[] ary, int offset) {
//        int value;
//        value = (int) ((ary[offset]&0xFF)
//                | ((ary[offset+1]<<8) & 0xFF00)
//                | ((ary[offset+2]<<16)& 0xFF0000)
//                | ((ary[offset+3]<<24) & 0xFF000000));
//        return value;
//    }

    public static int byte4ToInt(byte[] ary) {
        int value;

//        value = (int) ((ary[offset]&0xFF)
//                | ((ary[offset-1]<<8) & 0xFF00)
//                | ((ary[offset+2]<<16)& 0xFF0000)
//                | ((ary[offset+3]<<24) & 0xFF000000));
//        return value;


        value = (int)(((ary[0]<<24) & 0xFF000000)
                | ((ary[1]<<16) & 0xFF0000)
                | ((ary[2]<<8) & 0xFF00)
                | (ary[3]&0xFF)
        ) ;
        return value;
    }

    private void testDecode(){
       int result  =  3407890 ;
        byte[] bytes = intToBytes4(result) ;
        Log.i(TAG," testDecodeString:"+ printBytesData(bytes));

    }


}
