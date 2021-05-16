package scheduler.wgu.mywguscheduler.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scheduler.wgu.mywguscheduler.DAO.AssessmentDAO;
import scheduler.wgu.mywguscheduler.DAO.CourseDAO;
import scheduler.wgu.mywguscheduler.DAO.InstructorDAO;
import scheduler.wgu.mywguscheduler.DAO.TermDAO;
import scheduler.wgu.mywguscheduler.Entity.AssessmentEntity;
import scheduler.wgu.mywguscheduler.Entity.CourseEntity;
import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;
import scheduler.wgu.mywguscheduler.Entity.TermEntity;

@Database(entities = {AssessmentEntity.class, CourseEntity.class, InstructorEntity.class, TermEntity.class}, version = 1)
public abstract class ScheduleManagementDatabase extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract TermDAO termDAO();
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecuter =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile ScheduleManagementDatabase INSTANCE;

    static ScheduleManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ScheduleManagementDatabase.class, "schedule_management_database.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecuter.execute(() -> {
                InstructorDAO mInstructorDao = INSTANCE.instructorDAO();

                mInstructorDao.deleteAllInstructors();

                InstructorEntity instructor = new InstructorEntity(1,"Sophi Kilgrove","815-822-9937","skilgrove0@google.nl");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(2,"Celia Hecks","102-114-0555","checks1@theguardian.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(3,"Bryce Matula","771-905-6947","bmatula2@cloudflare.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(4,"Hansiain Baulcombe","904-449-7401","hbaulcombe3@alexa.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(5,"Beckie Turban","863-688-0598","bturban4@chicagotribune.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(6,"Chryste Tankard","946-448-8543","ctankard5@odnoklassniki.ru");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(7,"Dougie Itzig","304-573-4227","ditzig6@angelfire.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(8,"Morten Lowton","729-290-1518","mlowton7@ehow.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(9,"Yetta Dowman","593-651-2965","ydowman8@si.edu");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(10,"Alison Witheridge","662-313-4763","awitheridge9@state.tx.us");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(11,"Godard Backhurst","770-181-8331","gbackhursta@ihg.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(12,"Etienne Stellino","853-142-0643","estellinob@is.gd");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(13,"Lilian Berzon","528-265-3805","lberzonc@bigcartel.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(14,"Una Dashkovich","120-335-3309","udashkovichd@sakura.ne.jp");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(15,"Brander Frangleton","872-438-1857","bfrangletone@dailymail.co.uk");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(16,"Wade Wingeatt","129-789-3738","wwingeattf@java.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(17,"Carver Thrasher","998-113-0409","cthrasherg@prweb.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(18,"Teresita Jeeves","256-641-9343","tjeevesh@cbsnews.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(19,"Demetrius Prettejohns","140-451-0181","dprettejohnsi@typepad.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(20,"Matti Bazoche","284-986-6585","mbazochej@dailymail.co.uk");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(21,"Bertie Hunnam","902-113-3155","bhunnamk@weebly.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(22,"Lance Guillet","835-495-2211","lguilletl@rambler.ru");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(23,"Leanora Kneath","627-753-5699","lkneathm@google.co.uk");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(24,"Alvin Wilkinson","386-979-4505","awilkinsonn@usatoday.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(25,"Saunders Pinxton","597-393-9151","spinxtono@w3.org");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(26,"Kevan Dalbey","500-150-5763","kdalbeyp@booking.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(27,"Dar Gelland","248-697-5640","dgellandq@wikispaces.com");
                mInstructorDao.insert(instructor);
                instructor = new InstructorEntity(28,"Maire Frisch","261-748-5638","mfrischr@engadget.com");
                mInstructorDao.insert(instructor);

            });
        }
    };
}
