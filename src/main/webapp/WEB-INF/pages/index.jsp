<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>OpenChain</title>

        <script src="/resources/scripts/components/requirejs/require.js"
                data-main="/resources/scripts/main"></script>


        <meta property="og:image" content="http://openchain.info/resources/img/main-header.jpg" />


        <style>
            input:focus{
                outline: 0;
            }

            input::-webkit-input-placeholder {
                font-family:  Consolas;
                font-size: 20;
                color: #c6c6c6 !important;
            }

        </style>

    </head>

    <body>

        <div style="width:100%; height:90%;">

            <table width="100%" height="100%" align="center" valign="center">
                <tr>

                    <td style=" text-align: center">
                           <img  src="/resources/img/main-header.jpg" width="300px"/> <br/>

                            <div style="border: 1px solid #8b98f6;
                                width: 400px; display: inline-block;
                                border-radius: 20px">
                                &nbsp;
                                <input id="searchText"
                                        placeholder="Block index or any hash..."
                                        style="width: 330px; line-height: 40px;
                                        padding-left: 10px;

                                        border: none;">

                                <img  id="searchButton" src="/resources/img/main-webpac.png" width="35px" height="30px"
                                      style="vertical-align: middle; padding-bottom: 5px; cursor: pointer"/>
                            </div>
                    </td>
                </tr>
            </table>
            <script>require(['page/search']);</script>




        </div>
        <div style="border: 0px #0b0b0b solid; height: 10%; text-align: right; ">
               <span style="position: absolute; bottom: 10px; right: 15px; color: #c6c6c6; font-family: verdana; font-size: 11px">

                   Block chain open source explorer (<a href="https://github.com/romanman/openchain.info"
                                                        target="_blank">GitHub<a>)

               </span>
        </div>



    </body>

</html>