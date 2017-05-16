package com.ssinitsa.training.culinary.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IRecipeDetailsDao;
import com.ssinitsa.training.culinary.dao.xml.impl.exception.NotSupportedMethodException;
import com.ssinitsa.training.culinary.dao.xml.impl.wrapper.XmlModelWrapper;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class RecipeDetailsDaoXmlImpl implements IRecipeDetailsDao {

    private final XStream xstream = new XStream(new DomDriver());

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public RecipeDetails get(Integer id) {

        File file = getFile();

        XmlModelWrapper<Integer, RecipeDetails> wrapper = (XmlModelWrapper<Integer, RecipeDetails>) xstream.fromXML(file);
        List<RecipeDetails> details = wrapper.getRows();
        for (RecipeDetails recipeDetails : details) {
            if (recipeDetails.getId().equals(id)) {
                return recipeDetails;
            }
        }
        return null;

    }

    @Override
    public RecipeDetails insert(RecipeDetails recipeDetails) {
        File file = getFile();

        XmlModelWrapper<Integer, RecipeDetails> wrapper = (XmlModelWrapper<Integer, RecipeDetails>) xstream.fromXML(file);
        List<RecipeDetails> details = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        recipeDetails.setId(newId);
        details.add(recipeDetails);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return recipeDetails;

    }

    @Override
    public void update(RecipeDetails recipeDetails) {
        File file = getFile();

        XmlModelWrapper<Integer, RecipeDetails> wrapper = (XmlModelWrapper<Integer, RecipeDetails>) xstream.fromXML(file);
        List<RecipeDetails> details = wrapper.getRows();
        for (RecipeDetails recipeDetailsItem : details) {
            if (recipeDetailsItem.getId().equals(recipeDetails.getId())) {
                // TODO copy all properties
                break;
            }
        }

        writeNewData(file, wrapper);
    }


    @Override
    public void delete(Integer id) {
        File file = getFile();

        XmlModelWrapper<Integer, RecipeDetails> wrapper = (XmlModelWrapper<Integer, RecipeDetails>) xstream.fromXML(file);
        List<RecipeDetails> details = wrapper.getRows();
        RecipeDetails found = null;
        for (RecipeDetails recipeDetails : details) {
            if (recipeDetails.getId().equals(id)) {
                found = recipeDetails;
                break;
            }
        }
        if (found != null) {
            details.remove(found);
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
        File file = new File(rootFolder + "details.xml");
        return file;
    }

	@Override
	public List<RecipeWithDetails> getRecipeWithDetails(Integer id) {
		throw new NotSupportedMethodException();
	}

	@Override
	public List<RecipeDetails> getByRecipeId(Integer id) {
		throw new NotSupportedMethodException();
	}

	@Override
	public void clearRecipeDetails(Integer recipeId) {
		throw new NotSupportedMethodException();
	}

}
