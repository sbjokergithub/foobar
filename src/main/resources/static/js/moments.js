function showMoments() {
    pages --;
    console.log("BANG!");

    let ajaxGet = (url, callback) => {
        let xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                callback(xmlhttp.responseText);
            }
        }
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    };

    ajaxGet("http://localhost:8080/10002/moment?limit=" + pages * itemInPages, (text) => {
            console.log(text);
            let json = JSON.parse(text);
            let oldContainer = document.getElementById("momentContainer");
            let container = document.createElement("div");
            container.id = "momentContainer";
            let body = oldContainer.parentElement;
            body.removeChild(oldContainer);
            body.append(container);
            for (let i in json) {
                let time = document.createElement("div");
                time.class = "time";
                time.innerText = json[i].timestamp;

                let nickname = document.createElement("div");
                nickname.class = "nickname";
                nickname.innerText = json[i].person.nickname;

                let title = document.createElement("div");
                title.class = "title";
                title.innerText = json[i].paper.title;

                let content = document.createElement("div");
                content.class = "content";
                content.innerHtml = json[i].paper.content;

                let oneContainer = document.createElement("form");
                oneContainer.append(time);
                oneContainer.append(nickname);
                oneContainer.append(title);
                oneContainer.append(content);

                container.append(oneContainer);
            }
            end = false;
            loadMoreMoments()
        }
    )
}

function loadMoreMoments() {
    console.log("BANG!");

    let ajaxGet = (url, callback) => {
        let xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                callback(xmlhttp.responseText);

            }
        }
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    };

    ajaxGet("http://localhost:8080/10002/moment?skip=" + pages * itemInPages + "&limit=" + itemInPages, (text) => {
            console.log(text);
            let json = JSON.parse(text);
            let container = document.getElementById("momentContainer");
            if (json.length == 0) {
                let endContainer = document.createElement("p");
                endContainer.innerText = "没有更早的信息了~";
                end = true;
                container.appendChild(endContainer);
            }
            for (let i in json) {
                let time = document.createElement("div");
                time.class = "time";
                time.innerText = json[i].timestamp;

                let nickname = document.createElement("div");
                nickname.class = "nickname";
                nickname.innerText = json[i].person.nickname;

                let title = document.createElement("div");
                title.class = "title";
                title.innerText = json[i].paper.title;

                let content = document.createElement("div");
                content.class = "content";
                content.innerHtml = json[i].paper.content;

                let oneContainer = document.createElement("form");
                oneContainer.append(time);
                oneContainer.append(nickname);
                oneContainer.append(title);
                oneContainer.append(content);

                container.append(oneContainer);
            }
            pages++;
            synch = true;
            if (!end && getDocumentTop() + getWindowHeight() == getScrollTop()) {
                synch = false;
                loadMoreMoments();
            }
        }
    )
}