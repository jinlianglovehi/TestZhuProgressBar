package application.android.com.testzhuprogressbar.realtimeguidance;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import application.android.com.testzhuprogressbar.testormlite.DatabaseHelper;
import application.android.com.testzhuprogressbar.testormlite.FitnessLevelModel;

/**
 * Created by jinliang on 17-8-1.
 */

public class RealTimeGuidanceModelDao {
    private Context context;
    private Dao<RealTimeGuidanceModel, Integer> realTimeGuidanceModelIntegerDao;
    private DatabaseHelper helper;

    public RealTimeGuidanceModelDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            realTimeGuidanceModelIntegerDao = helper.getDao(RealTimeGuidanceModel.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param user
     */
    public void add(RealTimeGuidanceModel user)
    {
        try
        {
            realTimeGuidanceModelIntegerDao.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
