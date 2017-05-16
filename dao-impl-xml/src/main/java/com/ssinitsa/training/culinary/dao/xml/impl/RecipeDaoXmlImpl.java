package com.ssinitsa.training.culinary.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IRecipeDao;
import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.dao.xml.impl.exception.NotSupportedMethodException;
import com.ssinitsa.training.culinary.dao.xml.impl.wrapper.XmlModelWrapper;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class RecipeDaoXmlImpl implements IRecipeDao {

    private final XStream xstream = new XStream(new DomDriver());

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public Recipe get(Integer id) {

        File file = getFile();

        XmlModelWrapper<Integer, Recipe> wrapper = (XmlModelWrapper<Integer, Recipe>) xstream.fromXML(file);
        List<Recipe> recipes = wrapper.getRows();
        for (Recipe recipe : recipes) {
            if (recipe.getId().equals(id)) {
                return recipe;
            }
        }
        return null;

    }

    @Override
    public Recipe insert(Recipe recipe) {
        File file = getFile();

        XmlModelWrapper<Integer, Recipe> wrapper = (XmlModelWrapper<Integer, Recipe>) xstream.fromXML(file);
        List<Recipe> recipes = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        recipe.setId(newId);
        recipes.add(recipe);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return recipe;

    }

    @Override
    public void update(Recipe recipe) {
        File file = getFile();

        XmlModelWrapper<Integer, Recipe> wrapper = (XmlModelWrapper<Integer, Recipe>) xstream.fromXML(file);
        List<Recipe> recipes = wrapper.getRows();
        for (Recipe recipeItem : recipes) {
            if (recipeItem.getId().equals(recipe.getId())) {
                // TODO copy all properties
                break;
            }
        }

        writeNewData(file, wrapper);
    }

    @Override
    public List<Recipe> getAll() {
        File file = getFile();

        XmlModelWrapper<Integer, Recipe> wrapper = (XmlModelWrapper<Integer, Recipe>) xstream.fromXML(file);
        return wrapper.getRows();
    }

    @Override
    public void delete(Integer id) {
        File file = getFile();

        XmlModelWrapper<Integer, Recipe> wrapper = (XmlModelWrapper<Integer, Recipe>) xstream.fromXML(file);
        List<Recipe> recipes = wrapper.getRows();
        Recipe found = null;
        for (Recipe recipe : recipes) {
            if (recipe.getId().equals(id)) {
                found = recipe;
                break;
            }
        }
        if (found != null) {
            recipes.remove(found);
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
        File file = new File(rootFolder + "recipes.xml");
        return file;
    }

	@Override
	public List<Recipe> search(RecipeFilter recipeFilter) {
		throw new NotSupportedMethodException();
	}

}
