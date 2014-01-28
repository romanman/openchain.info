require(['main'], function() {
    require(['jquery',  'domReady'],

        function($) {

             $("#searchButton").click(function(){
                 searchRequest()
             });

              $("#searchText").keydown(function(event){
                  if (event.keyCode == 13){
                      searchRequest()
                  }
              });

        });

});



function searchRequest(){

    alert('searching hard for: ' + $("#searchText").val());

}
