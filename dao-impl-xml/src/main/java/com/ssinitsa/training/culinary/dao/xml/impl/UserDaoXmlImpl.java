package com.ssinitsa.training.culinary.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IUserDao;
import com.ssinitsa.training.culinary.dao.xml.impl.exception.NotSupportedMethodException;
import com.ssinitsa.training.culinary.dao.xml.impl.wrapper.XmlModelWrapper;
import com.ssinitsa.training.culinary.datamodel.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class UserDaoXmlImpl implements IUserDao {

    private final XStream xstream = new XStream(new DomDriver());

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public User get(Integer id) {

        File file = getFile();

        XmlModelWrapper<Integer, User> wrapper = (XmlModelWrapper<Integer, User>) xstream.fromXML(file);
        List<User> users = wrapper.getRows();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;

    }

    @Override
    public User insert(User user) {
        File file = getFile();

        XmlModelWrapper<Integer, User> wrapper = (XmlModelWrapper<Integer, User>) xstream.fromXML(file);
        List<User> users = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        user.setId(newId);
        users.add(user);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return user;

    }

    @Override
    public void update(User user) {
        File file = getFile();

        XmlModelWrapper<Integer, User> wrapper = (XmlModelWrapper<Integer, User>) xstream.fromXML(file);
        List<User> users = wrapper.getRows();
        for (User userItem : users) {
            if (userItem.getId().equals(user.getId())) {
                // TODO copy all properties
                break;
            }
        }

        writeNewData(file, wrapper);
    }

    @Override
    public List<User> getAll() {
        File file = getFile();

        XmlModelWrapper<Integer, User> wrapper = (XmlModelWrapper<Integer, User>) xstream.fromXML(file);
        return wrapper.getRows();
    }

    @Override
    public void delete(Integer id) {
        File file = getFile();

        XmlModelWrapper<Integer, User> wrapper = (XmlModelWrapper<Integer, User>) xstream.fromXML(file);
        List<User> users = wrapper.getRows();
        User found = null;
        for (User user : users) {
            if (user.getId().equals(id)) {
                found = user;
                break;
            }
        }
        if (found != null) {
            users.remove(found);
            writeNewData(file, wrapper);
        }
    }


    private void writeNewData(File file, XmlModelWrapper obj) {
        try {
            xstream.toXML(obj, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile() {
        File file = new File(rootFolder + "users.xml");
        return file;
    }

	@Override
	public Integer getByLogin(String login) {
		throw new NotSupportedMethodException();
	}

	@Override
	public User getUserData(String login, String password) {
		throw new NotSupportedMethodException();
	}

}
