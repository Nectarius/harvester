

var saveWord = function (){
  /*  $.ajax({
        type: "POST",
        url: "wordlist.data",
        dataType: "json",
        //cache: false,
        contentType: "application/json",
        success: function(data) {
            console.log("words update ...");
            self.words = ko.observableArray(data);
            *//*   $.each(data.dashboard, function(i,post){
             $('#tabPanelWrapper').append('<ul><li>' + data.id + '</li><li>' + data.name +'</li></ul>');
             });*//*
        },
        error: function(xhr, status, error) {
            console.log(xhr.status);
        }
    });*/
}

var AppViewModel = function (wordList) {
    var self = this;

    self.words = ko.observableArray(wordList);

    self.addWord = function() {
        self.words.push({ word: 'test', translate: 'test t' , whence: 'game of thrones' , priority : '1' });
        self.updateWords();
    };

    self.removeWord = function() {
        self.words.remove(this);
    }

    self.updateWords = function() {

        $.ajax({
            type: "GET",
            url: "wordlist.data",
            dataType: "json",
            //cache: false,
            contentType: "application/json",
            success: function(data) {
                console.log("words update ...");
                self.words.removeAll();
                ko.utils.arrayPushAll(self.words, data)
                /*   $.each(data.dashboard, function(i,post){
                 $('#tabPanelWrapper').append('<ul><li>' + data.id + '</li><li>' + data.name +'</li></ul>');
                 });*/
            },
            error: function(xhr, status, error) {
                console.log(xhr.status);
            }
        });
    }
}





var loadWordList = function() {
    $.ajax({
        type: "GET",
        url: "wordlist.data",
        dataType: "json",
        //cache: false,
        contentType: "application/json",
        success: function(data) {
            console.log("words coming ...");
            ko.applyBindings(new AppViewModel(data));
         /*   $.each(data.dashboard, function(i,post){
                $('#tabPanelWrapper').append('<ul><li>' + data.id + '</li><li>' + data.name +'</li></ul>');
            });*/
        },
        error: function(xhr, status, error) {
            console.log(xhr.status);
        }
    });


}

loadWordList();




