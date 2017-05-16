package com.ssinitsa.training.culinary.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IIngredientDao;
import com.ssinitsa.training.culinary.dao.xml.impl.exception.NotSupportedMethodException;
import com.ssinitsa.training.culinary.dao.xml.impl.wrapper.XmlModelWrapper;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class IngredientDaoXmlImpl implements IIngredientDao {

    private final XStream xstream = new XStream(new DomDriver());

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public Ingredient get(Integer id) {

        File file = getFile();

        XmlModelWrapper<Integer, Ingredient> wrapper = (XmlModelWrapper<Integer, Ingredient>) xstream.fromXML(file);
        List<Ingredient> ingredients = wrapper.getRows();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId().equals(id)) {
                return ingredient;
            }
        }
        return null;

    }

    @Override
    public Ingredient insert(Ingredient ingredient) {
        File file = getFile();

        XmlModelWrapper<Integer, Ingredient> wrapper = (XmlModelWrapper<Integer, Ingredient>) xstream.fromXML(file);
        List<Ingredient> ingredients = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        ingredient.setId(newId);
        ingredients.add(ingredient);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return ingredient;

    }

    @Override
    public void update(Ingredient ingredient) {
        File file = getFile();

        XmlModelWrapper<Integer, Ingredient> wrapper = (XmlModelWrapper<Integer, Ingredient>) xstream.fromXML(file);
        List<Ingredient> ingredients = wrapper.getRows();
        for (Ingredient ingredientItem : ingredients) {
            if (ingredientItem.getId().equals(ingredient.getId())) {
                // TODO copy all properties
                break;
            }
        }

        writeNewData(file, wrapper);
    }

    @Override
    public List<Ingredient> getAll() {
        File file = getFile();

        XmlModelWrapper<Integer, Ingredient> wrapper = (XmlModelWrapper<Integer, Ingredient>) xstream.fromXML(file);
        return wrapper.getRows();
    }

    @Override
    public void delete(Integer id) {
        File file = getFile();

        XmlModelWrapper<Integer, Ingredient> wrapper = (XmlModelWrapper<Integer, Ingredient>) xstream.fromXML(file);
        List<Ingredient> ingredients = wrapper.getRows();
        Ingredient found = null;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId().equals(id)) {
                found = ingredient;
                break;
            }
        }
        if (found != null) {
            ingredients.remove(found);
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
        File file = new File(rootFolder + "ingredients.xml");
        return file;
    }

	@Override
	public List<Ingredient> getByCategory(String category) {
		throw new NotSupportedMethodException();
	}
}
