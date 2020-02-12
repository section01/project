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

        /// 時刻の分割
        var openTimeArray = openTime.split(':');
        var closeTimeArray = closeTime.split(':');
        var breakTimeArray = breakTime.split(':');

        /// 分割した時刻の時間と分の変換
        var openTimeLeft = parseInt(openTimeArray[0], 10);
        var convOpenMinute = openTimeLeft*60;
        var openTimeRight = parseInt(openTimeArray[1], 10);
        var addOpenTime = openTimeLeft + openTimeRight;

        var closeTimeLeft = parseInt(closeTimeArray[0] ,10);
        var convCloseMinute = closeTimeLeft*60;
        var closeTimeRight = parseInt(closeTimeArray[1] ,10);
        var addCloseTime = closeTimeLeft + closeTimeRight;

        var breakTimeLeft = parseInt(breakTimeArray[0] ,10);
        var convBreakMinute = breakTimeLeft*60;
        var breakTimeRight = parseInt(breakTimeArray[1] ,10);
        var addBreakTime = breakTimeLeft + breakTimeRight;

        if (openTimeLeft == 0 && openTimeRight == 0 && closeTimeLeft == 0 && closeTimeRight == 0) {
            return;
        }

        if (convOpenMinute >= convCloseMinute) {
        	return;
        }

        let deffHour = (closeTimeLeft - openTimeLeft - breakTimeLeft);
        let deffMin = (closeTimeRight - openTimeRight - breakTimeRight);


        if (deffMin < 0) {
        	deffMin = -(deffMin);
        	deffMin = (60 - deffMin);
        }


        let disp = ('00'+ deffHour).slice(-2) + ':' + ('00'+ deffMin).slice(-2);
        $('#details' + i + '\\.totalTime').val(disp);
    });

});
