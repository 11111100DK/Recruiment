    // JavaScript để xử lý hiển thị/ẩn form đăng ký popup
    const closeSignupFormButton = document.getElementById('close-signup-form');
    const signupForm = document.getElementById('signup-form');
    const overlay = document.getElementById('overlay');
    const btn = document.getElementById('btn');

    btn.addEventListener('click', () => {
      signupForm.style.display = 'block';
      overlay.style.display = 'block';
    })

    closeSignupFormButton.addEventListener('click', () => {
      signupForm.style.display = 'none';
      overlay.style.display = 'none';
    });