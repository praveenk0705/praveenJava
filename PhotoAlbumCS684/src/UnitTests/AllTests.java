package UnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddAlbumTest.class, AddUserTest.class, DeleteAlbumTest.class, DeleteUserTest.class, LogInTest.class })
public class AllTests {

}
