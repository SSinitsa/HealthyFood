package com.ssinitsa.training.culinary.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IRatingDao;
import com.ssinitsa.training.culinary.dao.xml.impl.exception.NotSupportedMethodException;
import com.ssinitsa.training.culinary.dao.xml.impl.wrapper.XmlModelWrapper;
import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class RatingDaoXmlImpl implements IRatingDao {

    private final XStream xstream = new XStream(new DomDriver());

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public Rating get(Integer id) {

        File file = getFile();

        XmlModelWrapper<Integer, Rating> wrapper = (XmlModelWrapper<Integer, Rating>) xstream.fromXML(file);
        List<Rating> ratings = wrapper.getRows();
        for (Rating rating : ratings) {
            if (rating.getId().equals(id)) {
                return rating;
            }
        }
        return null;

    }

    @Override
    public Rating insert(Rating rating) {
        File file = getFile();

        XmlModelWrapper<Integer, Rating> wrapper = (XmlModelWrapper<Integer, Rating>) xstream.fromXML(file);
        List<Rating> ratings = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        rating.setId(newId);
        ratings.add(rating);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return rating;

    }

    @Override
    public void update(Rating rating) {
        File file = getFile();

        XmlModelWrapper<Integer, Rating> wrapper = (XmlModelWrapper<Integer, Rating>) xstream.fromXML(file);
        List<Rating> ratings = wrapper.getRows();
        for (Rating ratingItem : ratings) {
            if (ratingItem.getId().equals(rating.getId())) {
                // TODO copy all properties
                break;
            }
        }

        writeNewData(file, wrapper);
    }

    @Override
    public List<Rating> getAll() {
        File file = getFile();

        XmlModelWrapper<Integer, Rating> wrapper = (XmlModelWrapper<Integer, Rating>) xstream.fromXML(file);
        return wrapper.getRows();
    }

    @Override
    public void delete(Integer id) {
        File file = getFile();

        XmlModelWrapper<Integer, Rating> wrapper = (XmlModelWrapper<Integer, Rating>) xstream.fromXML(file);
        List<Rating> ratings = wrapper.getRows();
        Rating found = null;
        for (Rating rating : ratings) {
            if (rating.getId().equals(id)) {
                found = rating;
                break;
            }
        }
        if (found != null) {
            ratings.remove(found);
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
        File file = new File(rootFolder + "ratings.xml");
        return file;
    }

	@Override
	public List<RecipeWithRating> getRecipeWithRating(Integer id) {
		throw new NotSupportedMethodException();
	}

}
