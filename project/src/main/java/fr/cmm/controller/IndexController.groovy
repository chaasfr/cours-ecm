package fr.cmm.controller;

import javax.inject.Inject;

import fr.cmm.controller.form.SearchForm;
import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
    import fr.cmm.helper.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import fr.cmm.helper.Columns;
import fr.cmm.service.RecipeService;

import java.util.List;


@Controller
class IndexController {
    @Inject
    private RecipeService recipeService;

    @RequestMapping(["/index", "/"])
    String index(ModelMap model) {
        model.columns = randomColumns()

        'index'
    }

    @RequestMapping("/tags.json")
    @ResponseBody
    List<String> tags() {
        recipeService.findAllTags()
    }

    @RequestMapping("/recettes")
    String recettes(SearchForm searchForm, ModelMap model) {
        PageQuery pageQuery = new PageQuery(index: searchForm.getPageIndex()-1, tag: searchForm.getTag())

        Pagination pagination = new Pagination(pageIndex: searchForm.getPageIndex(),pageSize: pageQuery.getSize(), count: recipeService.countByQuery(pageQuery))
        pagination.setPageIndex(searchForm.getPageIndex());

        model.searchForm = searchForm
        model.recipes = recipeService.findByQuery(pageQuery)
        model.pagination = pagination

        'recettes'
    }

    @RequestMapping("/recette-du-moment")
    String recettesDuMoment(ModelMap model) {
        model.recipe = recipeService.findRandom(1).next()

        'recette-du-moment'
    }

    private Columns randomColumns() {
        Columns columns = new Columns()
        3.times { columns.add(recipeService.findRandom(10))}

        columns
    }

    @RequestMapping("/recette/{id}")
    String recette(@PathVariable("id") String id, ModelMap model) {
        Recipe recette = recipeService.findById(id)
        model.recipe = recette
        if(!recette) {
            throw new ResourceNotFoundException()
        } else {
            'recette'
        }
    }

    @RequestMapping("/contact")
    String contact() {
        'contact'
    }

    @RequestMapping("/404")
    String quatrecentquatre() {
        '404'
    }

    @RequestMapping("/500")
    String cinqcent() {
        '500'
    }

    @RequestMapping("/mentions-legales")
    String mentionsLegales() {
        'mentions-legales'
    }
}

