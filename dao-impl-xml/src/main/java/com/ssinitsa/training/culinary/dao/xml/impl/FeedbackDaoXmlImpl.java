package com.ssinitsa.training.culinary.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IFeedbackDao;
import com.ssinitsa.training.culinary.dao.xml.impl.exception.NotSupportedMethodException;
import com.ssinitsa.training.culinary.dao.xml.impl.wrapper.XmlModelWrapper;
import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class FeedbackDaoXmlImpl implements IFeedbackDao {

    private final XStream xstream = new XStream(new DomDriver());

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public Feedback get(Integer id) {

        File file = getFile();

        XmlModelWrapper<Integer, Feedback> wrapper = (XmlModelWrapper<Integer, Feedback>) xstream.fromXML(file);
        List<Feedback> feedbacks = wrapper.getRows();
        for (Feedback feedback : feedbacks) {
            if (feedback.getId().equals(id)) {
                return feedback;
            }
        }
        return null;

    }

    @Override
    public Feedback insert(Feedback feedback) {
        File file = getFile();

        XmlModelWrapper<Integer, Feedback> wrapper = (XmlModelWrapper<Integer, Feedback>) xstream.fromXML(file);
        List<Feedback> feedbacks = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        feedback.setId(newId);
        feedbacks.add(feedback);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return feedback;

    }

    @Override
    public void update(Feedback feedback) {
        File file = getFile();

        XmlModelWrapper<Integer, Feedback> wrapper = (XmlModelWrapper<Integer, Feedback>) xstream.fromXML(file);
        List<Feedback> feedbacks = wrapper.getRows();
        for (Feedback feedbackItem : feedbacks) {
            if (feedbackItem.getId().equals(feedback.getId())) {
                // TODO copy all properties
                break;
            }
        }

        writeNewData(file, wrapper);
    }

    @Override
    public List<Feedback> getAll() {
        File file = getFile();

        XmlModelWrapper<Integer, Feedback> wrapper = (XmlModelWrapper<Integer, Feedback>) xstream.fromXML(file);
        return wrapper.getRows();
    }

    @Override
    public void delete(Integer id) {
        File file = getFile();

        XmlModelWrapper<Integer, Feedback> wrapper = (XmlModelWrapper<Integer, Feedback>) xstream.fromXML(file);
        List<Feedback> feedbacks = wrapper.getRows();
        Feedback found = null;
        for (Feedback feedback : feedbacks) {
            if (feedback.getId().equals(id)) {
                found = feedback;
                break;
            }
        }
        if (found != null) {
            feedbacks.remove(found);
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
        File file = new File(rootFolder + "feedbacks.xml");
        return file;
    }

	@Override
	public List<RecipeWithFeedback> getRecipeWithFeedback(Integer id) {
		throw new NotSupportedMethodException();
	}

	@Override
	public void clearFeedbacks(Integer recipeId) {
		throw new NotSupportedMethodException();
		
	}

}
