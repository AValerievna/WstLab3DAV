package wst.lab1.ws;

import lombok.SneakyThrows;
import wst.lab1.dao.MenagerieDAO;
import wst.lab1.db.Menagerie;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebService
public class MenagerieService {
    private MenagerieDAO menagerieDAO;

    private void notNullArg(String argName, Object argValue) throws ServiceException {
        if (argValue == null) {
            String message = argName + " mustn`t be null";
            throw new ServiceException(message, new ServiceFault(message));
        }
    }

    private <T> T wrapException(Supplier<T> supplier) throws ServiceException {
        try {
            return supplier.produce();
        } catch (ServiceException exc) {
            throw exc;
        } catch (SQLException exc) {
            String message = "SQL exception: " + exc.getMessage() + "\nSQL state: " + exc.getSQLState();
            throw new ServiceException(message, exc, new ServiceFault(message));
        } catch (Exception exc) {
            String message = "Exception " + exc.getClass().getName() + ": " + exc.getMessage();
            throw new ServiceException(message, exc, new ServiceFault(message));
        }
    }

    private interface Supplier<T> {
        T produce() throws Exception;
    }

    @WebMethod
    @SneakyThrows
    public List<Menagerie> findAll() throws ServiceException {
        return wrapException(() -> menagerieDAO.findAllValues());
    }

    @WebMethod
    @SneakyThrows
    public List<Menagerie> filter(@WebParam(name = "id") Long id, @WebParam(name = "animal") String animal,
                                  @WebParam(name = "name") String name, @WebParam(name = "breed") String breed,
                                  @WebParam(name = "health") String health, @WebParam(name = "arrival") Date arrival)
            throws ServiceException {
        return wrapException(() -> menagerieDAO.filterValues(id, animal, name, breed, health, arrival));
    }

    @WebMethod
    @SneakyThrows
    public long create(@WebParam(name = "animal") String animal,
                       @WebParam(name = "name") String name, @WebParam(name = "breed") String breed,
                       @WebParam(name = "health") String health, @WebParam(name = "arrival") Date arrival)
            throws ServiceException {
        notNullArg("animal", animal);
        notNullArg("name", name);
        notNullArg("breed", breed);
        notNullArg("health", health);
        notNullArg("arrival", arrival);
        return wrapException(() -> menagerieDAO.create(animal, name, breed, health, arrival));
    }

    @WebMethod
    @SneakyThrows
    public int delete(@WebParam(name = "id") long id) throws ServiceException {
        return wrapException(() -> {
            int delCount = menagerieDAO.delete(id);
            if (delCount <= 0) {
                String message = String.format("Records with id %d not found", id);
                throw new ServiceException(message, new ServiceFault(message));
            }
            return delCount;
        });
    }

    @WebMethod
    @SneakyThrows
    @WebResult(name = "updatedCount")
    public int update(@WebParam(name = "id") Long id, @WebParam(name = "animal") String animal,
                      @WebParam(name = "name") String name, @WebParam(name = "breed") String breed,
                      @WebParam(name = "health") String health, @WebParam(name = "arrival") Date arrival)
            throws ServiceException {
        notNullArg("animal", animal);
        notNullArg("name", name);
        notNullArg("breed", breed);
        notNullArg("health", health);
        notNullArg("arrival", arrival);
        return wrapException(() -> {
            int updCount = menagerieDAO.update(id, animal, name, breed, health, arrival);
            if (updCount <= 0) {
                String message = String.format("Records with id %d not found", id);
                throw new ServiceException(message, new ServiceFault(message));
            }
            return updCount;
        });
    }

    public MenagerieService(MenagerieDAO menagerieDAO) {
        this.menagerieDAO = menagerieDAO;
    }

    public MenagerieService() {
    }
}
