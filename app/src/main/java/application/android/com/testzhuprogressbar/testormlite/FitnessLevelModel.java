package application.android.com.testzhuprogressbar.testormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import application.android.com.testzhuprogressbar.FitNessLevelEntity;

/**
 * Created by jinliang on 17-7-18.
 */

@DatabaseTable(tableName = FitNessLevelEntity.TABLE_NAME)
public class FitnessLevelModel {


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "gender")
    private int gender;
    @DatabaseField(columnName = "start_age")
    private int startAge;

    @DatabaseField(columnName = "end_age")
    private int endAge;

    @DatabaseField(columnName = "start_vo2_max")
    private int startVo2Max;

    @DatabaseField(columnName = "end_vo2_max")
    private int endVo2Max;

    @DatabaseField(columnName = "fitness_level")
    private int fitnessLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStartAge() {
        return startAge;
    }

    public void setStartAge(int startAge) {
        this.startAge = startAge;
    }

    public int getEndAge() {
        return endAge;
    }

    public void setEndAge(int endAge) {
        this.endAge = endAge;
    }

    public int getStartVo2Max() {
        return startVo2Max;
    }

    public void setStartVo2Max(int startVo2Max) {
        this.startVo2Max = startVo2Max;
    }

    public int getEndVo2Max() {
        return endVo2Max;
    }

    public void setEndVo2Max(int endVo2Max) {
        this.endVo2Max = endVo2Max;
    }

    public int getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(int fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    @Override
    public String toString() {
        return "FitnessLevelModel{" +
                "id=" + id +
                ", gender=" + gender +
                ", startAge=" + startAge +
                ", endAge=" + endAge +
                ", startVo2Max=" + startVo2Max +
                ", endVo2Max=" + endVo2Max +
                ", fitnessLevel=" + fitnessLevel +
                '}';
    }
}
