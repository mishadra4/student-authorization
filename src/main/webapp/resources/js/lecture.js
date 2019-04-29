var lectureComponent = {

    enrollCheckbox: function () {
        var url = $(this).attr('myContextPath') + "/lecture";
        console.log(url);
        $.ajax({
            url: url
        });
    }
};

/*
lectureComponent = {

    $(document).ready(function(){}),

    initEventListeners: function () {
        var _this = lectureComponent;

        $(document).on('change', '.change-js', _this.onStudentEnrollCheckbox);
    },

    onStudentEnrollCheckbox: function () {

    }

};*/
