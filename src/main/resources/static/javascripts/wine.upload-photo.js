var Wine = Wine || {}

Wine.UploadPhoto = (function() {
    function UploadPhoto() {
        this.uploadDrop = $('#upload-drop');
        this.containerPhoto = $('.js-container-photo');
    }

    UploadPhoto.prototype.init = function() {
        var settings = {
            type: 'json',
            fileLimit: 1,
            allow: '*.(jpg|jpeg|png)',
            action: '/photos/' + this.uploadDrop.data('code'),
            complete: uploadComplete.bind(this),
            beforeSend: addCsrfToken
        };

        UIkit.uploadSelect($('#upload-select'), settings);
        UIkit.uploadDrop(this.uploadDrop , settings);
    }

    function addCsrfToken(xhr) {
        var header = $('input[name=_csrf_header]').val();
        var token = $('input[name=_csrf]').val();
        xhr.setRequestHeader(header, token);
    }

    function uploadComplete(resp) {
        this.uploadDrop.addClass("hidden");
        this.containerPhoto.prepend('<img src="' + resp.url + '" class="img-responsive" style="margin: auto;"/>')
    }

    return UploadPhoto;
})();

$(function () {

    var uploadPhoto = new Wine.UploadPhoto();
    uploadPhoto.init();

})