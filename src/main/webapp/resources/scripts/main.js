require.config({
    baseUrl: '/resources/scripts',
    paths: {
        jquery:        '/resources/scripts/components/jquery/jquery-1.9.1',
        jqueryui:      '/resources/scripts/components/jquery/jquery-ui',
        jquerytooltip: '/resources/scripts/components/jquery/jquery.tooltipster',
        jquerytable: '/resources/scripts/components/jquery/jquery.dataTables',
        jquerysparkline: '/resources/scripts/components/jquery/jquery.sparkline',
        domReady: '/resources/scripts/components/requirejs/domReady'



    },
    shim: {



        jquerytable: {

            deps:  ['jquery']
        },


        jquerytooltip: {

            deps:  ['jquery']
        }


    }
   //, urlArgs: "bust=" +  (new Date()).getTime()
});


// StrangeKeyMain libs - Libraries and modules that will be needed on all pages of the site
require(['jquery', 'jqueryui',
    'jquerytooltip', 'jquerytable', 'domReady'], function() {

    console.log('main.js')

});

