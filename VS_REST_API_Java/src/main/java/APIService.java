import org.omg.CORBA.UserException;

import java.util.Collection;


public interface APIService {

    public boolean addEntity(Entity entity);

    public Collection<Entity> getEntities();

    public Collection<Entity> getEntity(String string);

    public Entity editEntity(Entity entity, String string)
            throws UserException;

    public void deleteEntity(String string);

    public void deleteEntities();

}
