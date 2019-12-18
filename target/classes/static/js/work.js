$(function() {

    $('body').css('padding-top',    $('header').outerHeight());
    $('body').css('padding-bottom', $('footer').outerHeight());

    $('#period').on('change', function() {
        let temp = $('#period').val().split('-');
        let year  = temp[0];
        let month = temp[1];

    });

    $('table tr').on('change', function() {

        let i = $(this).index();

        let openTime  = $('#details' + i + '\\.openTime').val();
        let closeTime = $('#details' + i + '\\.closeTime').val();
        let breakTime = $('#details' + i + '\\.breakTime').val();

        if (openTime === '' || closeTime === '' || breakTime === '') {
            return;
        }

        openTime.ary  = openTime.split(':');
        closeTime.ary = closeTime.split(':');
        breakTime.ary = breakTime.split(':');

        let deff_hour = (closeTime.ary[0] - openTime.ary[0]) - breakTime.ary[0];
        let deff_min  = (closeTime.ary[1] - openTime.ary[1]) - breakTime.ary[1];

        if (deff_min < 0) {
        	deff_hour -= 1;
        }

        let disp = deff_hour + ':' + deff_min;
        $('#details' + i + '\\.totalTime').val(disp);
    });

});
