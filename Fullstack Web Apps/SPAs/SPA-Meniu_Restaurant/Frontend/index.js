async function getContent(fragmentId, callback)
{
    let pages = 
    {
        home: getHomeHTML(),
        mancare: await getMancareHTML(),
        bauturi: await getBauturiHTML()
    };

    callback(pages[fragmentId]);
}

function loadContent()
{
    var contentDiv = document.getElementById("app"),
        fragmentId = location.hash.substr(1);

    getContent(fragmentId, function(content){
        contentDiv.innerHTML = content;
    });
}

if(!location.hash)
    location.hash = "#home";

loadContent();
window.addEventListener("hashchange", loadContent);