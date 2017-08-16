package application.android.com.testzhuprogressbar.testormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by jinliang on 17-7-19.
 */

public class FitnessLevelModelDao {

    private Context context;
    private Dao<FitnessLevelModel, Integer> fitnessLevelModelsDao;
    private DatabaseHelper helper;

    public FitnessLevelModelDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            fitnessLevelModelsDao = helper.getDao(FitnessLevelModel.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param user
     */
    public void add(FitnessLevelModel user)
    {
        try
        {
            fitnessLevelModelsDao.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
