
import com.hjow.game.reflexioner.mainClasses.RunManager;
public class ReflexionerIndex 
{
    public static void main(String[] args)
    {        
        try {
            RunManager.run(args, null, ReflexionerIndex.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }    
}