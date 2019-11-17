function changeCheckbox(student){

    var $firstname = document.getElementById("firstname").innerText;

    console.log(student);
    console.log($firstname);
    var studentData = {};

    $.ajax({
        type: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: '/enrollStudent',
        data: JSON.stringify(studentData),
        dataType: 'json'
    });
}
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
