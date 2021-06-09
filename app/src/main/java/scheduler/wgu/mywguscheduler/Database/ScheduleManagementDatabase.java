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
import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.Entity.Term;

@Database(entities = {Assessment.class, Course.class, Instructor.class, Term.class}, version = 2)
public abstract class ScheduleManagementDatabase extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract TermDAO termDAO();
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecuter =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile ScheduleManagementDatabase INSTANCE;

    public static synchronized ScheduleManagementDatabase getDatabase(final Context context) {
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

                Instructor instructor = new Instructor("Sophi Kilgrove","815-822-9937","skilgrove0@google.nl");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Celia Hecks","102-114-0555","checks1@theguardian.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Bryce Matula","771-905-6947","bmatula2@cloudflare.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Hansiain Baulcombe","904-449-7401","hbaulcombe3@alexa.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Beckie Turban","863-688-0598","bturban4@chicagotribune.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Chryste Tankard","946-448-8543","ctankard5@odnoklassniki.ru");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Dougie Itzig","304-573-4227","ditzig6@angelfire.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Morten Lowton","729-290-1518","mlowton7@ehow.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Yetta Dowman","593-651-2965","ydowman8@si.edu");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Alison Witheridge","662-313-4763","awitheridge9@state.tx.us");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Godard Backhurst","770-181-8331","gbackhursta@ihg.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Etienne Stellino","853-142-0643","estellinob@is.gd");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Lilian Berzon","528-265-3805","lberzonc@bigcartel.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Una Dashkovich","120-335-3309","udashkovichd@sakura.ne.jp");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Brander Frangleton","872-438-1857","bfrangletone@dailymail.co.uk");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Wade Wingeatt","129-789-3738","wwingeattf@java.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Carver Thrasher","998-113-0409","cthrasherg@prweb.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Teresita Jeeves","256-641-9343","tjeevesh@cbsnews.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Demetrius Prettejohns","140-451-0181","dprettejohnsi@typepad.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Matti Bazoche","284-986-6585","mbazochej@dailymail.co.uk");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Bertie Hunnam","902-113-3155","bhunnamk@weebly.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Lance Guillet","835-495-2211","lguilletl@rambler.ru");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Leanora Kneath","627-753-5699","lkneathm@google.co.uk");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Alvin Wilkinson","386-979-4505","awilkinsonn@usatoday.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Saunders Pinxton","597-393-9151","spinxtono@w3.org");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Kevan Dalbey","500-150-5763","kdalbeyp@booking.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Dar Gelland","248-697-5640","dgellandq@wikispaces.com");
                mInstructorDao.insert(instructor);
                instructor = new Instructor("Maire Frisch","261-748-5638","mfrischr@engadget.com");
                mInstructorDao.insert(instructor);

            });
        }
    };
}
