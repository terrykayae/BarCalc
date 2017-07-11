package uk.co.tezk.mybarcalc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmRecyclerViewAdapter;
import uk.co.tezk.mybarcalc.model.Category;

public class AdministerCategoryActivity extends AppCompatActivity implements RealmChangeListener<Realm> {
    Handler mHandler;
    private Realm realm;
    private Context mContext;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etDescription) EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer_category);

        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CategoryAdapter(this, realm.where(Category.class).findAllAsync(), true));

        mHandler = new Handler();

        realm.addChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm!=null && !realm.isClosed())
            realm.close();
    }

    public void handleClickSave(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Category category = realm.createObject(Category.class);
                category.setName(etName.getText().toString());
                category.setDescription(etDescription.getText().toString());

            }
        });
    }

    @Override
    public void onChange(Realm realm) {
        recyclerView.getAdapter().notifyDataSetChanged();
        etName.setText("");
        etDescription.setText("");
    }

    class CategoryAdapter extends RealmRecyclerViewAdapter <Category, CategoryAdapter.CategoryViewHolder> {
        OrderedRealmCollection <Category> data;

        public CategoryAdapter(@NonNull Context context, @Nullable OrderedRealmCollection data, boolean autoUpdate) {
            super(context, data, autoUpdate);
            mContext = context;
            this.data = data;
        }

        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.category_cardview, parent, false);
            return new CategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder categoryHolder, int position) {
            Category category = data.get(position);
            categoryHolder.tvName.setText(category.getName());
            categoryHolder.tvDescription.setText(category.getDescription());
        }

        class CategoryViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tvName) TextView tvName;
            @BindView(R.id.tvDescription) TextView tvDescription;
            public CategoryViewHolder(View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvName);
                tvDescription = itemView.findViewById(R.id.tvDescription);
//                ButterKnife.bind(context, itemView);
            }
        }
    }
}
