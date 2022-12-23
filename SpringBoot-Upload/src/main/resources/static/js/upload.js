(function () {
    // 文件上传
    document.querySelector("#file-input-btn").addEventListener("change", function (event) {
        var file = event.target.files[0]
        console.log(file)

        var form = new FormData()
        form.append("file", file)

        axios.post("/upload", form).then(function (res) {
            if (res.data.code === 0) {
                // 图片预览
                var image = document.querySelector("#image")
                image.setAttribute("src", res.data.data.url)
                image.style.display = 'block'
            }
        })
    });

    // 文件下载
    document.querySelector("#file-download-btn").addEventListener("click", function (event) {
        var url = document.querySelector("#image").getAttribute("src")
        // FileSaver
        saveAs(url)
    });

})();