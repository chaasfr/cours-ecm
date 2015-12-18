package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import static fr.cmm.SpringProfiles.INTEG;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
class RecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Inject
    private MongoCollection recipeCollection;

    @Before
    @After
    void clean() {
        recipeCollection.remove();
    }

    @Test
    void save() {
        recipeService.save(new Recipe(title: 'test recipe'))

        assert recipeCollection.findOne().as(Recipe).title == 'test recipe'
    }

    @Test
    void 'find By Id'() {
        recipeService.save(new Recipe(title: 'test recipe', id: '5672b65344ae4e8a715218c4'))
        assert recipeService.findById('5672b65344ae4e8a715218c4').getTitle() == 'test recipe'
    }

    @Test
    void 'find By Id With Invalid Id'(){
        String idInvalid="123pasvalide456"
        assert recipeService.findById(idInvalid) == null
    }

    @Test
    void 'find By Query' () {
        5.times { recipeService.save(new Recipe())}

        assert recipeService.findByQuery(new PageQuery()).size() == 5
    }

    @Test
    void 'count By Query'(){
        PageQuery pageQuery= new PageQuery(tag: 'Tag2')

        4.times { recipeService.save(new Recipe(tags: ['Tag1','Tag2']))}
        recipeService.save(new Recipe(tags:['Tag3','Tag4']))

        assert recipeService.countByQuery(pageQuery) == 4
    }

    @Test
    void 'find By Query With Custom PageSize'() {
        5.times {recipeService.save(new Recipe())}

        PageQuery pageQuery = new PageQuery(size: 2)

        assert recipeService.findByQuery(pageQuery).size() == 2
    }

    @Test
    void 'find By Query With Tag'() {
        2.times { recipeService.save(new Recipe(tags:['tag1']))}
        2.times { recipeService.save(new Recipe(tags:['tag2']))}
        recipeService.save(new Recipe(tags:['tag3']))

        PageQuery pageQuery = new PageQuery(tag:'tag1');

        assert recipeService.findByQuery(pageQuery).size() == 2
    }

    @Test
    void 'find All Tags'() {
        recipeService.save(new Recipe(tags:['tag1','tag2']))
        recipeService.save(new Recipe(tags:['tag2','tag3']))

        assert recipeService.findAllTags() == ['tag1','tag2','tag3']
    }
}