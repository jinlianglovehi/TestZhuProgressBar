package application.android.com.testzhuprogressbar.realtimeguidance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import application.android.com.testzhuprogressbar.FitNessLevelEntity;

/**
 * Created by jinliang on 17-8-1.
 */

@DatabaseTable(tableName = RealTimeGuidanceModel.TABLE_NAME)
public class RealTimeGuidanceModel {

    public static final String TABLE_NAME = "real_time_guidance_table" ;
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "phrase_number")
    private int phraseNumber;


    @DatabaseField(columnName = "guide_title")
    private String  guideTitle;

    @DatabaseField(columnName = "guide_desc")
    private String  guideDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhraseNumber() {
        return phraseNumber;
    }

    public void setPhraseNumber(int phraseNumber) {
        this.phraseNumber = phraseNumber;
    }

    public String getGuideTitle() {
        return guideTitle;
    }

    public void setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
    }

    public String getGuideDesc() {
        return guideDesc;
    }

    public void setGuideDesc(String guideDesc) {
        this.guideDesc = guideDesc;
    }


    @Override
    public String toString() {
        return "RealTimeGuidanceModel{" +
                "id=" + id +
                ", phraseNumber=" + phraseNumber +
                ", guideTitle='" + guideTitle + '\'' +
                ", guideDesc='" + guideDesc + '\'' +
                '}';
    }
}
