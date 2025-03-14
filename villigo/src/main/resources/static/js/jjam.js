/**
 * /jjam/charge.html 파일에 포함
 */

// 잼 충전하기 버튼 
document.addEventListener("DOMContentLoaded", function () {
    const chargeButtons = document.querySelectorAll(".btn-charge");

    chargeButtons.forEach(button => {
        button.addEventListener("click", function () {
            const amount = this.getAttribute("data-amount");

            // 알람창 표시
            const isConfirmed = confirm(`${amount} 젤리를 충전합니다.\n결제창으로 이동합니다.`);

            if (isConfirmed) {
                // 🌟 팝업 크기 설정
                const popupWidth = 500;
                const popupHeight = 700;

                // 📌 현재 화면 크기 가져오기
                const screenWidth = window.screen.width;
                const screenHeight = window.screen.height;

                // 🎯 팝업 위치 계산 (가운데 정렬)
                const left = (screenWidth - popupWidth) / 2;
                const top = (screenHeight - popupHeight) / 2;

                // 📌 결제 팝업 창 열기 (중앙 정렬)
                window.open(
                    `/jjam/payment?amount=${amount}`,
                    "paymentPopup",
                    `width=${popupWidth},height=${popupHeight},left=${left},top=${top},resizable=no,scrollbars=no`
                );
            }
        });
    });
});

