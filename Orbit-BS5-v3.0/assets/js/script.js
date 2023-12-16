const sumaryBtn = document.querySelector('.sumary-btn');
const experienceBtn = document.querySelector('.experience-btn');
const projectBtn = document.querySelector('.project-btn');
const skillBtn = document.querySelector('.skill-btn');

const htmlSessions = {
    summarySession: `
        <section class="section summary-section">
            <h2 class="section-title"><span class="icon-holder"><i class="fa-solid fa-user"></i></span>Career Profile</h2>
            <div class="summary">
                <p>Summarise your career here lorem ipsum dolor sit amet, consectetuer adipiscing elit. You can <a href="https://themes.3rdwavemedia.com/bootstrap-templates/resume/orbit-free-resume-cv-bootstrap-theme-for-developers/" target="_blank">download this free resume/CV template here</a>. Aenean commodo ligula eget dolor aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu.</p>
            </div><!--//summary-->
        </section><!--//section-->
    `,
    experienceSession: `
        <section class="section experiences-section">
                <h2 class="section-title"><span class="icon-holder"><i class="fa-solid fa-briefcase"></i></span>Experiences</h2>
                
                <div class="item">
                    <div class="meta">
                        <div class="upper-row">
                            <h3 class="job-title">Lead Developer</h3>
                            <div class="time">2023 - Present</div>
                        </div><!--//upper-row-->
                        <div class="company">Startup Hubs, San Francisco</div>
                    </div><!--//meta-->
                    <div class="details">
                        <p>Describe your role here lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo.</p>  
                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. </p>
                    </div><!--//details-->
                </div><!--//item-->
                
                <div class="item">
                    <div class="meta">
                        <div class="upper-row">
                            <h3 class="job-title">Senior Software Engineer</h3>
                            <div class="time">2018 - 2023</div>
                        </div><!--//upper-row-->
                        <div class="company">Google, London</div>
                    </div><!--//meta-->
                    <div class="details">
                        <p>Describe your role here lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem.</p>  
                        
                    </div><!--//details-->
                </div><!--//item-->
                
                <div class="item">
                    <div class="meta">
                        <div class="upper-row">
                            <h3 class="job-title">UI Developer</h3>
                            <div class="time">2016 - 2018</div>
                        </div><!--//upper-row-->
                        <div class="company">Amazon, London</div>
                    </div><!--//meta-->
                    <div class="details">
                        <p>Describe your role here lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem.</p>  
                    </div><!--//details-->
                </div><!--//item-->
                
            </section><!--//section-->
    `,
    projectSession: `
    <section class="section projects-section">
        <h2 class="section-title"><span class="icon-holder"><i class="fa-solid fa-archive"></i></span>Projects</h2>
        <div class="intro">
            <p>You can list your side projects or open source libraries in this section. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et ligula in nunc bibendum fringilla a eu lectus.</p>
        </div><!--//intro-->
        <div class="item">
            <span class="project-title"><a href="https://themes.3rdwavemedia.com/bootstrap-templates/startup/coderpro-bootstrap-5-startup-template-for-software-projects/" target="_blank">CoderPro</a></span> - <span class="project-tagline">A responsive website template designed to help developers launch their software projects. </span>
            
        </div><!--//item-->
        <div class="item">
            <span class="project-title"><a href="https://themes.3rdwavemedia.com/bootstrap-templates/startup/launch-bootstrap-5-template-for-saas-businesses/" target="_blank">Launch</a></span> - <span class="project-tagline">A responsive website template designed to help startups promote their products or services.</span>
        </div><!--//item-->
        <div class="item">
            <span class="project-title"><a href="https://themes.3rdwavemedia.com/bootstrap-templates/resume/devcard-bootstrap-5-vcard-portfolio-template-for-software-developers/" target="_blank">DevCard</a></span> - <span class="project-tagline">A portfolio website template designed for software developers.</span>
        </div><!--//item-->
        <div class="item">
            <span class="project-title"><a href="https://themes.3rdwavemedia.com/bootstrap-templates/startup/bootstrap-template-for-mobile-apps-nova-pro/" target="_blank">Nova Pro</a></span> - <span class="project-tagline">A responsive Bootstrap theme designed to help app developers promote their mobile apps</span>
        </div><!--//item-->
        <div class="item">
            <span class="project-title"><a href="http://themes.3rdwavemedia.com/website-templates/responsive-bootstrap-theme-web-development-agencies-devstudio/" target="_blank">DevStudio</a></span> - 
            <span class="project-tagline">A responsive website template designed to help web developers/designers market their services. </span>
        </div><!--//item-->
    </section><!--//section-->
    `,
    skillSession: `
    <section class="skills-section section">
        <h2 class="section-title"><span class="icon-holder"><i class="fa-solid fa-rocket"></i></span>Skills &amp; Proficiency</h2>
        <div class="skillset">        
            <div class="item">
                <h3 class="level-title">Python &amp; Django</h3>
                <div class="progress level-bar">
                    <div class="progress-bar theme-progress-bar" role="progressbar" style="width: 99%" aria-valuenow="99" aria-valuemin="0" aria-valuemax="100"></div>
                </div>                               
            </div><!--//item-->
            
            <div class="item">
                <h3 class="level-title">Javascript</h3>
                <div class="progress level-bar">
                    <div class="progress-bar theme-progress-bar" role="progressbar" style="width: 98%" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100"></div>
                </div>                              
            </div><!--//item-->
            
            <div class="item">
                <h3 class="level-title">React &amp; Angular</h3>
                <div class="progress level-bar">
                    <div class="progress-bar theme-progress-bar" role="progressbar" style="width: 98%" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100"></div>
                </div>                                 
            </div><!--//item-->
            
            <div class="item">
                <h3 class="level-title">HTML5 &amp; CSS</h3>
                <div class="progress level-bar">
                        <div class="progress-bar theme-progress-bar" role="progressbar" style="width: 95%" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"></div>
                </div>                                
            </div><!--//item-->
            
            <div class="item">
                <h3 class="level-title">Ruby on Rails</h3>
                <div class="progress level-bar">
                    <div class="progress-bar theme-progress-bar" role="progressbar" style="width: 85%" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"></div>
                </div>                                  
            </div><!--//item-->
            
            <div class="item">
                <h3 class="level-title">Sketch &amp; Photoshop</h3>
                <div class="progress level-bar">
                    <div class="progress-bar theme-progress-bar" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                </div>                                 
            </div><!--//item-->
            
        </div>  
    </section><!--//skills-section-->
    `
}

function addComponent (innerHtml) {
    if (innerHtml != null && innerHtml !== "") {
        const mainWrapper = document.querySelector('.main-wrapper');
        const subWrapper = document.createElement('div');

        subWrapper.innerHTML = innerHtml;
        mainWrapper.appendChild(subWrapper);
    }
}

sumaryBtn.onclick = function () {
    addComponent(htmlSessions.summarySession);
} 
experienceBtn.onclick = function () {
    addComponent(htmlSessions.experienceSession);
} 
projectBtn.onclick = function () {
    addComponent(htmlSessions.projectSession);
} 
skillBtn.onclick = function () {
    addComponent(htmlSessions.skillSession);
}
