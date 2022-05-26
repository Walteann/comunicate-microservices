import bcript from 'bcrypt';

import User from '../../modules/user/model/User.js';

export async function createInitialData() {

    try {

        await User.sync({force: true});
    
        let password = await bcript.hash('123456', 10);
    
        await User.create({
            name: 'User test 1',
            email: 'testeuser1@gmail.com',
            password
        });

        await User.create({
            name: 'User test 2',
            email: 'testeuser2@gmail.com',
            password
        });

    } catch(err) {
        console.log(err);
    }


}

