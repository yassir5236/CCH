-- -- Insert Teams
-- INSERT INTO teams (id, team_name) VALUES
--                                       ('7e7d1a00-0000-0000-0000-000000000001', 'Team A'),
--                                       ('7e7d1a00-0000-0000-0000-000000000002', 'Team B'),
--                                       ('7e7d1a00-0000-0000-0000-000000000003', 'Team C');
--
-- -- Insert Cyclists
-- INSERT INTO cyclists (id, first_name, last_name, age, nationality, team_id) VALUES
--                                                                                 ('a1b2c3d4-0000-0000-0000-000000000001', 'John', 'Doe', '1990-05-12', 'USA', '7e7d1a00-0000-0000-0000-000000000001'),
--                                                                                 ('a1b2c3d4-0000-0000-0000-000000000002', 'Jane', 'Smith', '1988-10-22', 'France', '7e7d1a00-0000-0000-0000-000000000002'),
--                                                                                 ('a1b2c3d4-0000-0000-0000-000000000003', 'Carlos', 'Garcia', '1995-07-19', 'Spain', '7e7d1a00-0000-0000-0000-000000000003');
--
-- -- Insert Competitions
-- INSERT INTO competitions (id, competition_name, date, place, distance) VALUES
--                                                                            ('9f8e7d6c-0000-0000-0000-000000000001', 'Tour de France', '2024-07-01', 'France', 3500),
--                                                                            ('9f8e7d6c-0000-0000-0000-000000000002', 'Giro d\'Italia', '2024-05-15', 'Italy', 3200);
--
-- -- Insert Stages
-- INSERT INTO stages (id, stage_number, start_location, end_location, start_date_time, stage_type, competition_id) VALUES
--   ('d1e2f3a4-0000-0000-0000-000000000001', 1, 'Nice', 'Marseille', '2024-07-01 08:00:00', 'FLAT', '9f8e7d6c-0000-0000-0000-000000000001'),
--   ('d1e2f3a4-0000-0000-0000-000000000002', 2, 'Marseille', 'Lyon', '2024-07-02 08:00:00', 'MOUNTAIN', '9f8e7d6c-0000-0000-0000-000000000001'),
--   ('d1e2f3a4-0000-0000-0000-000000000003', 1, 'Turin', 'Milan', '2024-05-15 09:00:00', 'TIME_TRIAL', '9f8e7d6c-0000-0000-0000-000000000002');
--
-- -- Insert General Results
-- INSERT INTO general_results (cyclist_id, competition_id, general_time, general_rank) VALUES
--   ('a1b2c3d4-0000-0000-0000-000000000001', '9f8e7d6c-0000-0000-0000-000000000001', 'PT72H30M', 1),
--   ('a1b2c3d4-0000-0000-0000-000000000002', '9f8e7d6c-0000-0000-0000-000000000001', 'PT73H10M', 2),
--   ('a1b2c3d4-0000-0000-0000-000000000003', '9f8e7d6c-0000-0000-0000-000000000002', 'PT70H50M', 1);
--
-- -- Insert Results (Stage-specific)
-- INSERT INTO results (cyclist_id, stage_id, time, rank) VALUES
--   ('a1b2c3d4-0000-0000-0000-000000000001', 'd1e2f3a4-0000-0000-0000-000000000001', 'PT3H45M', 1),
--   ('a1b2c3d4-0000-0000-0000-000000000002', 'd1e2f3a4-0000-0000-0000-000000000001', 'PT4H10M', 2),
--   ('a1b2c3d4-0000-0000-0000-000000000003', 'd1e2f3a4-0000-0000-0000-000000000003', 'PT2H55M', 1);







<!--<beans xmlns="http://www.springframework.org/schema/beans"-->
<!--       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"-->
<!--       xmlns:context="http://www.springframework.org/schema/context"-->
<!--       xmlns:tx="http://www.springframework.org/schema/tx"-->
<!--       xsi:schemaLocation="http://www.springframework.org/schema/beans-->
<!--        http://www.springframework.org/schema/beans/spring-beans.xsd-->
<!--        http://www.springframework.org/schema/context-->
<!--        http://www.springframework.org/schema/context/spring-context.xsd-->
<!--        http://www.springframework.org/schema/tx-->
<!--        http://www.springframework.org/schema/tx/spring-tx.xsd">-->

<!--    &lt;!&ndash; Configuration IOC et DataSource &ndash;&gt;-->
<!--    <context:component-scan base-package="org.example" />-->

<!--    &lt;!&ndash; Configuration de la transaction &ndash;&gt;-->
<!--    <tx:annotation-driven transaction-manager="transactionManager" />-->

<!--    &lt;!&ndash; Configuration de la source de données &ndash;&gt;-->
<!--    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">-->
<!--        <property name="driverClassName" value="org.postgresql.Driver" />-->
<!--        <property name="url" value="jdbc:postgresql://localhost:5432/CCH" />-->
<!--        <property name="username" value="postgres" />-->
<!--        <property name="password" value="User_Password" />-->
<!--    </bean>-->

<!--    &lt;!&ndash; SessionFactory Hibernate &ndash;&gt;-->
<!--    <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">-->
<!--        <property name="dataSource" ref="dataSource" />-->
<!--        <property name="packagesToScan" value="org.example.entity" />-->
<!--        <property name="hibernateProperties">-->
<!--            <props>-->
<!--                <prop key="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</prop>-->
<!--                <prop key="hibernate.hbm2ddl.auto">update</prop>-->
<!--                <prop key="hibernate.show_sql">true</prop>-->
<!--            </props>-->
<!--        </property>-->
<!--    </bean>-->

<!--    &lt;!&ndash; Gestionnaire de transactions &ndash;&gt;-->
<!--    <bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">-->
<!--        <property name="sessionFactory" ref="sessionFactory" />-->
<!--    </bean>-->

<!--</beans>-->
