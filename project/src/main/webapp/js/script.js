
$(function(){
    $('input[data-role="tags"]').each(function() {
        var input = $(this);

        $.getJSON(input.data('service-url'), function(data) {
            input.tokenfield({autocomplete: {
                source: data,
                delay: 100
            }});
        })
    });

    $('button[data-role="addIngredient"]').click(function() {
        // FIX-JSP-9
        var index = $('#ingredients > div').length;

        $('#ingredients').append($('<div>').load('/admin/recettes/ingredientFormRow?ingredientIndex=' + index));
    });

    $(document).on('click', 'button[data-role="removeIngredient"]', function() {
        $(this).parents('.row')[0].remove();
    });
});


