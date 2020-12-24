$(document).ready(function () {
    var mtree = $('ul.mtree');

    // Skin selector for demo
    mtree.wrap('<div class=mtree-demo></div>');

    mtree.addClass(skins[0]);
    $('body').prepend('<div class="mtree-skin-selector"><ul class="button-group radius"></ul></div>');
    var s = $('.mtree-skin-selector');


    s.find('button.skin').each(function (index) {
        $(this).on('click.mtree-skin-selector', function () {
            s.find('button.skin.active').removeClass('active');
            $(this).addClass('active');
            mtree.removeClass(skins.join(' ')).addClass(skins[index]);
        });
    })
    s.find('button:first').addClass('active');
    s.find('.csl').on('click.mtree-close-same-level', function () {
        $(this).toggleClass('active');
    });
});