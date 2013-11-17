
var wordsBinding = function(wordList){
    ko.applyBindings({
        words: wordList
          //  [
          //  { word: 'Bert', translate: 'Bertington' , whence: 'game of thrones' , priority : '1' },
         //   { word: 'Bert', translate: 'Bertington' , whence: 'game of thrones' , priority : '2' }
       // ]
    });
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
            wordsBinding(data);
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




